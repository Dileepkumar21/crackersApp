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
