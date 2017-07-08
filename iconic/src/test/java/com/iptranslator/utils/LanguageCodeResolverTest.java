package com.iptranslator.utils;

import com.iptranslator.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by rdas on 08/07/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
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
}