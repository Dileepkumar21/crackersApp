package com.pos.crackers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pos.crackers.entities.SaleResponse;
import com.pos.crackers.model.converter.SaleResponseJsonConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SALE",schema = "POS_DATA")
public class Sale extends AuditBaseEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALE_ID")
    Long Id;

    @ManyToMany(mappedBy = "sales")
    private List<Crackers> crackers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "sale")
    private Order order;

    @Column(name = "SALE_VALUE")
    private Integer saleValue;

    @Column(name = "ACTUAL_COST")
    private Integer actualCost;

    //TODO - Add non null constraint on this in the database
    @Column(name = "sale_details", columnDefinition = "jsonb")
    @Convert(converter = SaleResponseJsonConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private SaleResponse saleResponse;

    public List<Crackers> getCrackers() {
        return crackers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Integer saleValue) {
        this.saleValue = saleValue;
    }

    public void addSoldCrackers(Crackers crackers){
        this.crackers.add(crackers);
    }

    public SaleResponse getSaleResponse() {
        return saleResponse;
    }

    public void setSaleResponse(SaleResponse saleResponse) {
        this.saleResponse = saleResponse;
    }

    public Integer getActualCost() {
        return actualCost;
    }

    public void setActualCost(Integer actualCost) {
        this.actualCost = actualCost;
    }

    public Sale() {
        this.crackers = new ArrayList<>();
    }

    public Sale(Customer customer, Integer saleValue, Integer actualCost) {
        this.customer = customer;
        this.saleValue = saleValue;
        this.actualCost = actualCost;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "customer=" + customer +
                ", saleValue=" + saleValue +
                ", actualCost=" + actualCost +
                '}';
    }
}
