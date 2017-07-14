package hello.service;

import com.rdas.config.AppYamlSettings;
import com.rdas.config.YamlLanguageSettings;
import hello.config.TestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
//@WebAppConfiguration
public class ApplicationYamlSettingsTest {

    @Autowired
    private AppYamlSettings appYamlSettings;
    @Autowired
    private YamlLanguageSettings yamlLanguageSettings;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(appYamlSettings);
        Assert.assertNotNull(yamlLanguageSettings);

        Assert.assertEquals(appYamlSettings.getName(), "SampleServiceComponentRd");
        Assert.assertEquals("0.0.99", appYamlSettings.getVersion());
    }

    @Test
    public void assertThatlanguageCodeIsMapped() {
        Assert.assertNotNull(yamlLanguageSettings);
        Assert.assertTrue("Kilgray Codes mapped", yamlLanguageSettings.getLanguageMapKilgray().size()>0);
    }
}