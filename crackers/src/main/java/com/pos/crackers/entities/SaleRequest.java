package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
@ToString
public class SaleRequest {

    @JsonProperty("CrackerItems")
    private List<CrackerItem> crackerItemList;

    @JsonProperty("CustomerInfo")
    private CustomerInfo customerInfo;

    public List<CrackerItem> getCrackerItemList() {
        return crackerItemList;
    }

    public void setCrackerItemList(List<CrackerItem> crackerItemList) {
        this.crackerItemList = crackerItemList;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public SaleRequest(List<CrackerItem> crackerItemList, CustomerInfo customerInfo) {
        this.crackerItemList = crackerItemList;
        this.customerInfo = customerInfo;
    }
}
