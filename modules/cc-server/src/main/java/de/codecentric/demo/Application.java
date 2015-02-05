package de.codecentric.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/")
    ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
