package de.codecentric.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

        @RequestMapping("/")
        void redirect(HttpServletResponse response) throws IOException {
            response.sendRedirect("/public/index.html");
        }
    }

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("de.codecentric.demo")
    static class ApplicationConfiguration extends WebMvcConfigurationSupport {

        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/public/");
        }
    }
}
