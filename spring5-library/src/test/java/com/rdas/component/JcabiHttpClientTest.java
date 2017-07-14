package com.rdas.component;

import hello.config.TestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class JcabiHttpClientTest {

    @Autowired
    private JcabiHttpClient jcabiHttpClient;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(jcabiHttpClient);
    }

    @Test
    public void executeGetString() throws IOException {
        String url = "https://www.google.com/test";
        String path = "/users";
        String queryParam = "id";
        String value = "333";
        String s = jcabiHttpClient.fetchStringByGetRequest(url, path, queryParam, value);
        Assert.assertNotNull(s);
    }
}