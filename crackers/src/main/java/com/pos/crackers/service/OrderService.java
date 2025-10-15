package com.pos.crackers.service;

import com.pos.crackers.entities.CustomerInfo;
import com.pos.crackers.entities.SaleRequest;
import com.pos.crackers.model.Customer;
import com.pos.crackers.model.Order;
import com.pos.crackers.repo.CustomerRepository;
import com.pos.crackers.repo.OrderRepository;
import com.pos.crackers.service.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    Util utils;

    public void submitOrder(SaleRequest saleRequest){
        CustomerInfo customerInfo = saleRequest.getCustomerInfo();

        Customer customer = utils.getCustomer(customerInfo);
        Order order = new Order();
        order.setOrderPack(saleRequest);
        order.setCustomer(customer);
        order.setFulfilled(false);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
