package com.rdas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ObjectMessage;

@Service
public class StringMessageSender {
    private static final Logger logger = LoggerFactory.getLogger(StringMessageSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String text) {
        logger.info("Setting message to mq {}", text);
        jmsTemplate.send(session -> {
            ObjectMessage objectMessage = session.createObjectMessage(text);
            return objectMessage;
        });
    }
}
