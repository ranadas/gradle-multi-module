package com.rdas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootJmsApplication {
	private static final Logger logger = LoggerFactory.getLogger(BootJmsApplication.class);
	public static void main(String[] args) throws Exception {
		logger.info("BootJmsApplication:: Starting ...");
		ApplicationContext ctx = SpringApplication.run(BootJmsApplication.class, args);

//		MessageSender msgSender = (MessageSender)ctx.getBean("messageSender");
//
//		// Send message to a JMS queue
//		msgSender.sendMessage("This is the first msg \n");

		// Give it some time to process
		//TimeUnit.MINUTES.sleep(2);
		logger.info("BootJmsApplication:: the end ...");
	}
}
