package com.iptranslator.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by rdas on 08/07/2017.
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.iptranslator"})
@ConfigurationProperties(prefix = "langcodes")
public class TestConfig {

    // WIP - bean construct with bingLanguageMapping seems null. ConfigurationProperties is set after bean is created.
    // should be before the bean is created.
    @NotNull
    @Valid
    private Map<String, String> bingLanguageMapping;
    public void setBingLanguageMapping(Map<String, String> bingLanguageMapping) {
        this.bingLanguageMapping = bingLanguageMapping;
    }

    @PostConstruct
    public void init() {
        System.out.println(bingLanguageMapping.toString());
    }
    @Bean(name = "bingLanguageMapping")
    public Map<String, String> getBingLanguageMapping() {
        return bingLanguageMapping;
    }
}
