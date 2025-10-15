package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This entity is used as a DTO to transfer entire sale list
 * to the caller
 */
@NoArgsConstructor
@Component
public class TotalSalesResponse {

    @JsonProperty("sales")
    private List<SaleItem> saleItemList;

    @JsonProperty
    private Integer totalSaleValue;

    public Integer getTotalSaleValue() {
        return totalSaleValue;
    }

    public void setTotalSaleValue(Integer sellValue) {
        totalSaleValue = sellValue;
    }

    public List<SaleItem> getSaleItemList() {
        return saleItemList;
    }

    public void setSaleItemList(List<SaleItem> saleItemList) {
        this.saleItemList = saleItemList;
    }


}
