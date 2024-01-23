package com.musala.droneschecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DronesCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DronesCheckerApplication.class, args);
    }

}
