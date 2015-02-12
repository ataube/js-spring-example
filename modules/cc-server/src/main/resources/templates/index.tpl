def templateRootPath = developerMode ? 'public/dev/' : ''

yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Demo')

        if(developerMode) {
            link(rel: 'stylesheet', href: '/public/dev/app.css')

            script(type: 'text/javascript', src: '/public/dev/angular/angular.js') {}
            script(type: 'text/javascript', src: '/public/dev/angular-route/angular-route.js') {}
            script(type: 'text/javascript', src: '/public/dev/main/main_controller.js') {}
            script(type: 'text/javascript', src: '/public/dev/components/app_service.js') {}
            script(type: 'text/javascript', src: '/public/dev/app.js') {}

        } else {

            link(rel: 'stylesheet', href: '/public/app.css')

            script(type: 'text/javascript', src: '/public/lib.js') {}
            script(type: 'text/javascript', src: '/public/app.js') {}
            script(type: 'text/javascript', src: '/public/templates.js') {}
        }

        script { yieldUnescaped """

                angular.module('cc-app-config', []).constant('appConfig', {
                   developerMode: $developerMode,
                   templateRootPath: '$templateRootPath'
                });

            """
        }
    }
    body('ng-app': 'cc-app') {

        div(class: 'container', 'ng-view': '') {}

    }
}