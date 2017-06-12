package com.rdas.controller;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * https://github.com/cosmic-cowboy/spring-boot-multipart-file-upload/blob/master/spring-boot-multipart-file-upload/src/test/java/com/slgerkamp/introductory/spring/boot/multipart/file/upload/application/controller/MultipartControllerTest.java
 * Created by rdas on 11/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FileUploadControllerTest {

    private final Logger logger = LoggerFactory.getLogger(FileUploadControllerTest.class);

    @Value("${local.server.port}")
    int port;

    @Before
    public void init() {
        RestAssured.port = port;
    }

    @Test
    public void uploadASingleFile() {

    }
}