package com.rdas.service;

import com.rdas.config.JmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class StringMessageReceiver {
    private static final Logger logger = LoggerFactory.getLogger(StringMessageReceiver.class);

    @JmsListener(destination = JmsConfig.COMMENT_QUEUE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        String response = message.getPayload();
        logger.info("Message received {}", response);
    }
}
