var gulp = require('gulp');
var plugins = require("gulp-load-plugins")({
    pattern: ['gulp-*', 'del'],
    lazy:false
});

var paths = {
    dest: 'buildjs/static/public'
}

gulp.task('scripts', function(){
    //combine all js files of the app
    gulp.src(['!./app/**/*_test.js','./app/**/*.js'])
        .pipe(plugins.jshint())
        .pipe(plugins.jshint.reporter('default'))
        .pipe(plugins.concat('app.js'))
        .pipe(gulp.dest(paths.dest));
});

gulp.task('templates',function(){
    //combine all template files of the app into a js file
    gulp.src(['./app/**/*.html'])
        .pipe(plugins.angularTemplatecache('templates.js',{standalone:false, module: 'cc-app'}))
        .pipe(gulp.dest(paths.dest));
});

gulp.task('css', function(){
    gulp.src('./app/**/*.css')
        .pipe(plugins.concat('app.css'))
        .pipe(gulp.dest(paths.dest));
});

gulp.task('vendorJS', function(){
    //concatenate vendor JS files
		//NOTE: Using a glob pattern might cause a invalid concatenation order
		//      of the vendor dependencies => use https://github.com/ck86/main-bower-files to solve this issue
    gulp.src(['./bower_components/angular/angular.js', './bower_components/angular-route/angular-route.js'])
        .pipe(plugins.concat('lib.js'))
        .pipe(gulp.dest(paths.dest));
});

gulp.task('vendorCSS', function(){
    //concatenate vendor CSS files
    gulp.src(['!./bower_components/**/*.min.css',
        './bower_components/**/*.css'])
        .pipe(plugins.concat('lib.css'))
        .pipe(gulp.dest(paths.dest));
});

gulp.task('watch',function(){
    gulp.watch([
        'build/**/*.html',        
        'build/**/*.js',
        'build/**/*.css'        
    ], function(event) {
        return gulp.src(event.path)
            .pipe(plugins.connect.reload());
    });
    gulp.watch(['./app/**/*.js','!./app/**/*test.js'],['scripts']);
    gulp.watch(['./app/**/*.html'],['templates']);
    gulp.watch('./app/**/*.css',['css']);

});

gulp.task('connect', plugins.connect.server({
    root: ['build'],
    port: 9000,
    livereload: true
}));

gulp.task('clean', function (done) {
    plugins.del([paths.dest], done);
});

gulp.task('build',['scripts','templates','css','vendorJS','vendorCSS']);

gulp.task('default', ['clean'], function () {
    gulp.start('build');
});
