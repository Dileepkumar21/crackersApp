package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("actualCost")
    private Integer actualCost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("saleValue")
    private Integer saleValue;

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

    public Integer getActualCost() {
        return actualCost;
    }

    public void setActualCost(Integer actualCost) {
        this.actualCost = actualCost;
    }

    public Integer getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Integer saleValue) {
        this.saleValue = saleValue;
    }

    public SaleRequest(List<CrackerCost> crackerCostList, CustomerInfo customerInfo, Integer totalCost, Integer saleValue) {
        this.crackerCostList = crackerCostList;
        this.customerInfo = customerInfo;
        this.actualCost = totalCost;
        this.saleValue = saleValue;
    }
}
