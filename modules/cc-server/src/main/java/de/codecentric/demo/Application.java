package de.codecentric.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationConfiguration.class);
    }

    @RestController
    static class ThingsJsonController {

        @RequestMapping("/api/things")
        ResponseEntity<List<String>> awesomeThings() {

            List<String> things = new ArrayList<String>();
            things.add("HTML5 Boilerplate");
            things.add("AngularJS");
            things.add("Karma");

            return new ResponseEntity<>(things, HttpStatus.OK);
        }
    }

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("de.codecentric.demo")
    static class ApplicationConfiguration extends WebMvcConfigurationSupport {

        @Autowired
        Environment environment;

        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {

            Boolean developerMode = environment.getProperty("developerMode", Boolean.class);
            String appRoot = environment.getProperty("projectRoot") + "/modules/cc-app";

            if(developerMode) {
                registry.addResourceHandler("/public/dev/**")
                        .addResourceLocations("file://" + appRoot + "/bower_components/")
                        .addResourceLocations("file://" + appRoot + "/app/");
            } else {
                registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/public/");
            }
        }

        @Override
        protected void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("index");

        }

        @Override
        protected void configureViewResolvers(ViewResolverRegistry registry) {
            Map<String, Object> map = new HashMap<>();
            map.put("developerMode", environment.getProperty("developerMode", Boolean.class));
            registry.groovy().attributes(map);
        }


    }
}
