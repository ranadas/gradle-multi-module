package com.iptranslator.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rdas on 08/07/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.rdas", "com.iptranslator"})
@EnableAutoConfiguration
public class TestConfig {
}
