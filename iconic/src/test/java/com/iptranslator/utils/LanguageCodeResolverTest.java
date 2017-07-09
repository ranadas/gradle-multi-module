package com.iptranslator.utils;

import com.iptranslator.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rdas on 08/07/2017.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("dev")

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, initializers = ConfigFileApplicationContextInitializer.class)
public class LanguageCodeResolverTest {

    @Autowired
    private LanguageCodeResolver languageCodeResolver;

    @Before
    public void init() throws Exception {
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
}