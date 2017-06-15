package com.rdas;

import com.github.tomaslanger.chalk.Chalk;
import com.rdas.service.StringMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootJmsApplication {
    private static final Logger logger = LoggerFactory.getLogger(BootJmsApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("" + Chalk.on("\n" +
                "* BootJmsApplication:: Starting ... * \n").green());

        ApplicationContext ctx = SpringApplication.run(BootJmsApplication.class, args);
        StringMessageSender msgSender = ctx.getBean(StringMessageSender.class);//(StringMessageSender) ctx.getBean("messageSender");
        // Send message to a JMS queue
        msgSender.sendMessage("This is the first msg \n");

        // Give it some time to process
        //TimeUnit.MINUTES.sleep(2);
        logger.info("" + Chalk.on("\n" +
                "*BootJmsApplication:: the end ...* \n").green());
    }
}
