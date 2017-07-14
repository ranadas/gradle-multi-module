package com.iptranslator.utils;

import com.iptranslator.config.LanguageCodeSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rdas on 08/07/2017.
 * TODO : should really return Optional instead of null.
 */
@Service
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "langcodes")
public class LanguageCodeResolver {
    private final Logger logger = LoggerFactory.getLogger(LanguageCodeResolver.class);

    public final static String STANDARD_6392B = "639-2/B";
    public final static String STANDARD_KILGRAY = "Kilgray";
//    /**
//     * Standard ISO language codes: http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
//     * <p>
//     * Our language codes are based on 639-1
//     */
//
//    //Column 639-1
//    private Map<String, String> languageMap6391;
//
//    //Column 639-2/B
//    private Map<String, String> languageMap6392B;
//
//    public void setLanguageMap6392B(Map<String, String> languageMap6392B) {
//        this.languageMap6392B = languageMap6392B;
//    }
//
//    /**
//     * Kilgray language codes for MemoQPlugin
//     */
//    @NotNull
//    @Valid
//    private Map<String, String> languageMapKilgray;
//
//    public void setLanguageMapKilgray(Map<String, String> languageMapKilgray) {
//        this.languageMapKilgray = languageMapKilgray;
//    }
//
//    @NotNull
//    @Valid
//    private Map<String, String> bingLanguageMapping;
//    public void setBingLanguageMapping(Map<String, String> bingLanguageMapping) {
//        this.bingLanguageMapping = bingLanguageMapping;
//    }
//    public Map<String, String> getBingMapping() {
//        return Collections.unmodifiableMap(bingLanguageMapping);
//    }
//
//    @PostConstruct
//    public void handlersLikeXml() {
//        languageMap6391 = Collections.unmodifiableMap(new HashMap<String, String>() {
//            {
//                put("en", "en");
//                put("fr", "fr");
//                put("de", "de");
//                put("pt", "pt");
//                put("es", "es");
//                put("zhcn", "zhcn");
//                put("zhtw", "zhtw");
//                put("ja", "ja");
//                put("ko", "ko");
//                put("ru", "ru");
//                put("ga", "ga");
//                put("el", "el");
//                put("nl", "nl");
//                put("ro", "ro");
//            }
//        });
//        logger.info(" in LanguageCodeResolver Post Construct!!!");
//    }

    @Autowired
    private LanguageCodeSettings codeSettings;

    public boolean areValidLanguages(String from, String to) {
        if (codeSettings.getLanguageMap6391().containsKey(from) && codeSettings.getLanguageMap6391().containsKey(to)) {
            return true;
        } else if (codeSettings.getLanguageMap6392B().containsKey(from) && codeSettings.getLanguageMap6392B().containsKey(to)) {
            return true;
        } else if (containsCaseInsensitive(from, codeSettings.getLanguageMapKilgray()) && containsCaseInsensitive(to, codeSettings.getLanguageMapKilgray())) {
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
            return codeSettings.getLanguageMap6392B().entrySet()
                    .parallelStream()
                    .filter(e -> languages.contains(e.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } else if (LanguageCodeResolver.STANDARD_KILGRAY.equals(languagesCodeStandard)) {
            return codeSettings.getLanguageMapKilgray().entrySet()
                    .parallelStream()
                    .filter(e -> languages.contains(e.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } else {
            return languages;
        }
    }

    public String getStandardLanguageCode(String lang) {
        lang = lang.toLowerCase();
        if (codeSettings.getLanguageMap6391().containsKey(lang)) {
            return lang;
        } else if (codeSettings.getLanguageMap6392B().containsKey(lang)) {
            return codeSettings.getLanguageMap6392B().get(lang);
        } else if (containsCaseInsensitive(lang, codeSettings.getLanguageMapKilgray())) {
            return getValueCaseInsensitive(lang, codeSettings.getLanguageMapKilgray());
        } else {
            return null;
        }
    }

    private String getValueCaseInsensitive(String code, Map<String, String> map) {
        return map.entrySet()
                .parallelStream()
                .filter(e -> e.getValue().equalsIgnoreCase(code))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
