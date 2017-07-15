package hello.service;

import com.github.tomaslanger.chalk.Chalk;
import com.rdas.config.AppYamlSettings;
import com.rdas.config.LanguageCodeSettings;
import hello.config.TestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
//@WebAppConfiguration
public class ApplicationYamlSettingsTest {
    private final Logger logger = LoggerFactory.getLogger(ApplicationYamlSettingsTest.class);
    @Autowired
    private AppYamlSettings appYamlSettings;
    @Autowired
    private LanguageCodeSettings yamlLanguageSettings;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(appYamlSettings);
        Assert.assertNotNull(yamlLanguageSettings);

        Assert.assertEquals(appYamlSettings.getName(), "SampleServiceComponentRd");
        Assert.assertEquals("0.0.99", appYamlSettings.getVersion());
    }

    @Test
    public void assertThatlanguageCodeIsSetFromYaml() {
        Assert.assertNotNull(yamlLanguageSettings);
        Assert.assertTrue("Kilgray Codes mapped", yamlLanguageSettings.getLanguageMapKilgray().size()>0);
    }

    @Test
    public void assertThat6391languageCodeIsSetFromYaml() {
        logger.info("{}", Chalk.on("\n\t--> Checking LanguageMap6391 is Set!" ).blue());
        Assert.assertNotNull(yamlLanguageSettings.getLanguageMap6391());
    }
}