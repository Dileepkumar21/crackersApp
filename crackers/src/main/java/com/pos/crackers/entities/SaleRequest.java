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

    @JsonProperty("crackerCostList")
    private List<CrackerCost> crackerCostList;

    @JsonProperty("customerInfo")
    private CustomerInfo customerInfo;

    @JsonProperty("totalCost")
    private Integer totalCost;

    public List<CrackerCost> getCrackerCostList() {
        return crackerCostList;
    }

    public void setCrackerCostList(List<CrackerCost> crackerCostList) {
        this.crackerCostList = crackerCostList;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public SaleRequest(List<CrackerCost> crackerCostList, CustomerInfo customerInfo, Integer totalCost) {
        this.crackerCostList = crackerCostList;
        this.customerInfo = customerInfo;
        this.totalCost = totalCost;
    }
}
