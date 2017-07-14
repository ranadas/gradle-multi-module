package com.rdas.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Getter @Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "langcodes")
public class YamlLanguageSettings {
    private Map<String, String> languageMapKilgray;
    private Map<String, String> languageMap6392B;
    private Map<String, String> bingLanguageMapping;
}
