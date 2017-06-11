package com.rdas.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.fest.assertions.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rdas on 11/06/2017.
 * https://spring.io/guides/gs/testing-web/
 * http://blog.codeleak.pl/2013/11/controlleradvice-improvements-in-spring.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloController controller;

    @LocalServerPort
    private int port;

    @Test
    public void contexLoads() throws Exception {
        assertThat(restTemplate).isNotNull();
        assertThat(controller).isNotNull();
    }

    //http://www.lucassaldanha.com/unit-and-integration-tests-in-spring-boot/
    @Test
    public void assetThatHelloReturnsSuccess() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
//                String.class)).contains("Hello World");

        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
        ;
        /*
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/hello", new CreateClientRequest("Foo"), String.class);
        String client = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Foo", client.getName());
        */
    }
}