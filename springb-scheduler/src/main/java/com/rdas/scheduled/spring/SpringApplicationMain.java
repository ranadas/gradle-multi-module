package com.rdas.scheduled.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by rdas on 31/07/2017.
 * Although scheduled tasks can be embedded in web apps and WAR files,
 * the simpler approach demonstrated below creates a standalone application.
 *
 * @EnableScheduling ensures that a background task executor is created.
 * Without it, nothing gets scheduled.

 */
@SpringBootApplication
@EnableScheduling
public class SpringApplicationMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringApplicationMain.class);
    }
}
