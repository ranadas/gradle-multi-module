package com.rdas.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service")
public class AppYamlSettings {
    //name: SampleServiceComponentRd
    //version:  0.0.99
    private String name;
    private String version;
}
