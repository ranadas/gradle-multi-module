package com.rdas.jmsa.service;

import com.rdas.jmsa.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by x148128 on 30/05/2017.
 */
@Service
public class StoreService {

    private final List<Order> receivedOrders = new ArrayList<>();

    public void registerOrder(Order order) {
        this.receivedOrders.add(order);
    }

    public Optional<Order> getReceivedOrder(String id) {
        return receivedOrders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }
}
