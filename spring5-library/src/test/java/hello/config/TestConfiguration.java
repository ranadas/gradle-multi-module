package hello.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {"com.rdas"})
@EnableAutoConfiguration
public class TestConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("stop");
    }
}
