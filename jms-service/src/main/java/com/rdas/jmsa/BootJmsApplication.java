package com.rdas.jmsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by x148128 on 30/05/2017.
 */
@EnableSwagger2
@SpringBootApplication
public class BootJmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootJmsApplication.class, args);
    }
}
