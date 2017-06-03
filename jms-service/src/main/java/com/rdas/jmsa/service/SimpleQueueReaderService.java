package com.rdas.jmsa.service;

import com.rdas.jmsa.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by x148128 on 30/05/2017.
 * Responsible for sending a new order to the JMS queue. it uses a JmsTemplate:
 */
@Service
public class SimpleQueueReaderService {

    private static final String SIMPLE_QUEUE = "simple.queue";

    @Autowired
    private StoreService storeService;

    @JmsListener(destination = SIMPLE_QUEUE)
    public void receiveOrder(Order order) {
        storeService.registerOrder(order);
    }
}