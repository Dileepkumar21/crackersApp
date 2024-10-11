package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class CrackerItem {

    @JsonProperty(value = "crackerName")
    private String crackerName;

    @JsonProperty(value = "quantity")
    private Integer quantity;

    public String getCrackerName() {
        return crackerName;
    }

    public void setCrackerName(String crackerName) {
        this.crackerName = crackerName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CrackerItem(String crackerName, Integer quantity) {
        this.crackerName = crackerName;
        this.quantity = quantity;
    }
}
