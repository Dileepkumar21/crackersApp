package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
public class CrackerCost {

    @JsonProperty("crackerItem")
    private CrackerItem crackerItem;

    @JsonProperty("cost")
    private Integer cost;

    public CrackerItem getCrackerItem() {
        return crackerItem;
    }

    public void setCrackerItem(CrackerItem crackerItem) {
        this.crackerItem = crackerItem;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public CrackerCost(CrackerItem crackerItem, Integer cost) {
        this.crackerItem = crackerItem;
        this.cost = cost;
    }
}
