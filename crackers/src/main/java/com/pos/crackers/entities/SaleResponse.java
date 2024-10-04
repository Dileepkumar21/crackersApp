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

    @JsonProperty(value = "totalCost")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalCost;

    private List<CrackerCost> crackerCostList;

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public List<CrackerCost> getCrackerCostList() {
        return crackerCostList;
    }

    public void setCrackerCostList(List<CrackerCost> crackerCostList) {
        this.crackerCostList = crackerCostList;
    }
}

