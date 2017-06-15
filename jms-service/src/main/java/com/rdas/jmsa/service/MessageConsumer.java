package com.rdas.jmsa.service;

import com.rdas.jmsa.config.JmsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

//import javax.jms.Message;

@Service
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @PostConstruct
    public void init() {
        logger.info("READING ");
    }

    @JmsListener(destination = JmsConfiguration.DB_QUEUE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        String response = message.getPayload();
        logger.info("\n\n--> ***** {}", response);
    }
}
