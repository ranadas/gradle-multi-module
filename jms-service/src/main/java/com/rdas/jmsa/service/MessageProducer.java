package com.rdas.jmsa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
//import javax.jms.Message;

@Service
public class MessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        logger.info("STOP {}", jmsTemplate.toString());
        sendMessage("add a message ");
    }

    public void sendMessage(final String text) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(text);
                return objectMessage;
            }
        });
    }
}
