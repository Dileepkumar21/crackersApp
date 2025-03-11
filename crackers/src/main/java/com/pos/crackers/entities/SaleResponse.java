package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {

    @JsonProperty(value = "saleValue")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer saleValue;

    @JsonProperty(value = "actualCost")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer actualCost;

    private List<CrackerCost> crackerCostList;

    public Integer getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Integer saleValue) {
        this.saleValue = saleValue;
    }

    public Integer getActualCost() {
        return actualCost;
    }

    public void setActualCost(Integer actualCost) {
        this.actualCost = actualCost;
    }

    public List<CrackerCost> getCrackerCostList() {
        return crackerCostList;
    }

    public void setCrackerCostList(List<CrackerCost> crackerCostList) {
        this.crackerCostList = crackerCostList;
    }
}

