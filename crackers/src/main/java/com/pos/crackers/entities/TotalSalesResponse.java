package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class TotalSalesResponse {

    @JsonProperty
    private Customer customer;

    @JsonProperty
    private List<CrackerCost> crackers;

    @JsonProperty
    private Integer SellValue;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getSellValue() {
        return SellValue;
    }

    public void setSellValue(Integer sellValue) {
        SellValue = sellValue;
    }
}
