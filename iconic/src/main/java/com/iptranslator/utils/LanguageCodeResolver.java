package com.iptranslator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rdas on 08/07/2017.
 */
@Service
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "langcodes")
public class LanguageCodeResolver {
    private final Logger logger = LoggerFactory.getLogger(LanguageCodeResolver.class);

    public final static String STANDARD_6392B = "639-2/B";
    public final static String STANDARD_KILGRAY = "Kilgray";
    /**
     * Standard ISO language codes: http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
     * <p>
     * Our language codes are based on 639-1
     */

    //Column 639-1
    private Map<String, String> languageMap6391;

    //Column 639-2/B
    private Map<String, String> languageMap6392B;

    /**
     * Kilgray language codes for MemoQPlugin
     */
    @NotNull
    @Valid
    private Map<String, String> languageMapKilgray;
    public void setLanguageMapKilgray(Map<String, String> languageMapKilgray) {
        this.languageMapKilgray = languageMapKilgray;
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
        languageMap6392B = Collections.unmodifiableMap(new HashMap<String, String>() {
            {
                put("eng", "en");
                put("fre", "fr");
                put("ger", "de");
                put("por", "pt");
                put("spa", "es");
                put("chi_zh", "zhcn");
                put("chi_tw", "zhtw");
                put("jpn", "ja");
                put("kor", "ko");
                put("rus", "ru");
                put("gle", "ga");
            }
        });
        logger.info(" in LanguageCodeResolver Post Construct!!!");
    }

    public boolean areValidLanguages(String from, String to) {
        if (languageMap6391.containsKey(from) && languageMap6391.containsKey(to)) {
            return true;
        } else if (languageMap6392B.containsKey(from) && languageMap6392B.containsKey(to)) {
            return true;
        } else if (containsCaseInsensitive(from, languageMapKilgray) && containsCaseInsensitive(to, languageMapKilgray)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean containsCaseInsensitive(String code, Map<String, String> map) {
        return map.entrySet()
                .parallelStream()
                .filter(e -> e.getValue().equalsIgnoreCase(code))
                .findFirst().isPresent();
    }

    public List<String> getLanguages(String languagesCodeStandard, List<String> languages) {
        if (LanguageCodeResolver.STANDARD_6392B.equals(languagesCodeStandard)) {
            return languageMap6392B.entrySet()
                    .parallelStream()
                    .filter(e -> languages.contains(e.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } else if (LanguageCodeResolver.STANDARD_KILGRAY.equals(languagesCodeStandard)) {
            return languageMapKilgray.entrySet()
                    .parallelStream()
                    .filter(e -> languages.contains(e.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } else {
            return languages;
        }
    }
}
