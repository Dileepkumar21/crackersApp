package com.pos.crackers.entities;

import com.pos.crackers.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResponse {

    private List<Order> orders;

    public OrderResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addToOrders(Order order) {
        orders.add(order);
    }
}
