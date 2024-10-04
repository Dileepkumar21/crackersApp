package com.pos.crackers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pos.crackers.enums.PriceTypeEnum;
import com.pos.crackers.model.converter.PriceTypeEnumConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CRACKERS",schema = "POS_DATA")
public class Crackers extends AuditBaseEntity{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRACKER_ID")
    Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY_AVAILABLE")
    private Integer quantityAvailable;

    @Column(name = "ITEM_PRICE")
    private Integer itemPrice;

    @Column(name = "STOCK_PRICE")
    private Integer stockPrice;

    @Convert(converter = PriceTypeEnumConverter.class)
    @Column(name = "PRICE_TYPE")
    private PriceTypeEnum priceType;

    //TODO - When making a sale, all the other sale entries are getting saved again. Only the update_ts is getting
    // updated though
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "CRACKER_SALE",
            schema = "pos_data",
            joinColumns = @JoinColumn(name = "CRACKER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SALE_ID")
    )
    private List<Sale> sales = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Integer stockPrice) {
        this.stockPrice = stockPrice;
    }

    public PriceTypeEnum getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceTypeEnum priceType) {
        this.priceType = priceType;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addToSales(Sale sale) {
        this.sales.add(sale);
    }

    public Crackers() {
    }

    public Crackers(String name, Integer quantityAvailable, Integer itemPrice, Integer stockPrice, PriceTypeEnum priceType) {
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.itemPrice = itemPrice;
        this.stockPrice = stockPrice;
        this.priceType = priceType;
    }

    @Override
    public String toString() {
        return "Crackers{" +
                "name='" + name + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                ", itemPrice=" + itemPrice +
                ", stockPrice=" + stockPrice +
                ", priceType=" + priceType +
                '}';
    }
}
