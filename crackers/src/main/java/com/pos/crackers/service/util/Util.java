package com.pos.crackers.service.util;

import com.pos.crackers.entities.CustomerInfo;
import com.pos.crackers.model.Customer;
import com.pos.crackers.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    PersistenceService persistenceService;

    public Customer getCustomer(CustomerInfo customerInfo) {
        Customer customer = persistenceService.getCustomer(customerInfo.getName(), customerInfo.getPhoneNumber());

        if(customer==null){
            customer = persistenceService.saveCustomer(new Customer(customerInfo.getName(), customerInfo.getPhoneNumber(), customerInfo.getAddress()));
        }
        return customer;
    }
}
