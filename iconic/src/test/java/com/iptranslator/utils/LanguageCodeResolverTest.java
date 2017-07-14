package com.iptranslator.utils;

import com.iptranslator.config.LanguageCodeSettings;
import com.iptranslator.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by rdas on 08/07/2017.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("dev")

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class LanguageCodeResolverTest {

    @Autowired
    private LanguageCodeResolver languageCodeResolver;

    @Autowired
    private LanguageCodeSettings  codeSettings;

    private List<String> languages;

    @Before
    public void init() throws Exception {
        languages = Arrays.asList("eng", "es", "de", "fr", "it");
        System.out.printf(codeSettings.getBingMapping().toString());
    }

    @Test
    public void assertThatCoderResolverIsInjected() {
        Assert.assertTrue(languageCodeResolver != null);
    }

    @Test
    public void assertThatEnIsValidCode() {
        Assert.assertTrue(languageCodeResolver.areValidLanguages("en", "fr"));
        Assert.assertTrue(languageCodeResolver.areValidLanguages("zhtw", "zhtw"));
        Assert.assertTrue(languageCodeResolver.areValidLanguages("spa", "eng"));
        Assert.assertTrue(languageCodeResolver.areValidLanguages("gle", "eng"));
    }

    @Test
    public void assertThatUnknownLangCodeWillReturnSamelanguageSet() {
        List<String> languageSet = languageCodeResolver.getLanguages("NON_STD_CODE", languages);
        assertThat(languageSet.size(), is(5));
        assertThat(languageSet, is(languages));
    }

    @Test
    public void assertThatKilgrayCodeWillReturn13EnglanguageSet() {
        languages = Arrays.asList("en");
        List<String> languageSet = languageCodeResolver.getLanguages("Kilgray", languages);
        assertThat(languageSet.size(), is(13));
    }

    @Test
    public void assertThatKilgrayCodeWillReturn20EslanguageSet() {
        languages = Arrays.asList("es");
        List<String> languageSet = languageCodeResolver.getLanguages("Kilgray", languages);
        assertThat(languageSet.size(), is(20));
    }

    @Test
    public void assertThatArbitararyCodeWillReturnNullLangCode() {
        String ga = languageCodeResolver.getStandardLanguageCode("gaxx");
        Assert.assertNull(ga);
    }

    @Test
    public void assertThatLangCodeWillReturnKnownLangCode() {
        String ga = languageCodeResolver.getStandardLanguageCode("ga");
        assertThat(ga, is("ga"));
        String gle = languageCodeResolver.getStandardLanguageCode("gle");
        assertThat(gle, is("ga"));
    }
}