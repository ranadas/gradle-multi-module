package com.iptranslator.config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rdas on 13/07/2017.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "langcodes")
public class LanguageCodeSettings {
    private final Logger logger = LoggerFactory.getLogger(LanguageCodeSettings.class);
//    @Getter
//    public final static String STANDARD_6392B = "639-2/B";
//    @Getter
//    public final static String STANDARD_KILGRAY = "Kilgray";

    /**
     * Standard ISO language codes: http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
     * <p>
     * Our language codes are based on 639-1
     */
    //Column 639-1
    @Getter
    private Map<String, String> languageMap6391;

    //Column 639-2/B
    @Getter
    private Map<String, String> languageMap6392B;
    public void setLanguageMap6392B(Map<String, String> languageMap6392B) {
        this.languageMap6392B = languageMap6392B;
    }

    /**
     * Kilgray language codes for MemoQPlugin
     */
    @NotNull
    @Valid
    private Map<String, String> languageMapKilgray;

    public void setLanguageMapKilgray(Map<String, String> languageMapKilgray) {
        this.languageMapKilgray = languageMapKilgray;
    }

    public Map<String, String> getLanguageMapKilgray() {
        return Collections.unmodifiableMap(languageMapKilgray);
    }


    @NotNull
    @Valid
    private Map<String, String> bingLanguageMapping;

    public void setBingLanguageMapping(Map<String, String> bingLanguageMapping) {
        this.bingLanguageMapping = bingLanguageMapping;
    }

    public Map<String, String> getBingMapping() {
        return Collections.unmodifiableMap(bingLanguageMapping);
    }

    @PostConstruct
    public void handlersLikeXml() {
        languageMap6391 = Collections.unmodifiableMap(new HashMap<String, String>() {
            {
                put("en", "en");
                put("fr", "fr");
                put("de", "de");
                put("pt", "pt");
                put("es", "es");
                put("zhcn", "zhcn");
                put("zhtw", "zhtw");
                put("ja", "ja");
                put("ko", "ko");
                put("ru", "ru");
                put("ga", "ga");
                put("el", "el");
                put("nl", "nl");
                put("ro", "ro");
            }
        });
        logger.info(" in LanguageCodeResolver Post Construct!!!");
    }
}
