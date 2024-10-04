package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SaleItem {

    @JsonProperty
    private CustomerInfo customer;

    @JsonProperty
    private SaleResponse saleResponse;

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public SaleResponse getSaleResponse() {
        return saleResponse;
    }

    public void setSaleResponse(SaleResponse saleResponse) {
        this.saleResponse = saleResponse;
    }

    public SaleItem(CustomerInfo customer, SaleResponse saleResponse) {
        this.customer = customer;
        this.saleResponse = saleResponse;
    }

}
