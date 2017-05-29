package com.rdas.grmultimodule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.rdas.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

    @Test
    public void contextLoads() {
        Product product = Product.builder().name("test product").build();
    }

}
