package com.pos.crackers.model;

import com.pos.crackers.entities.CrackerItem;
import com.pos.crackers.entities.SaleResponse;
import com.pos.crackers.model.converter.SaleResponseJsonConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Fetch;

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

    @Column(name = "SELL_VALUE")
    private Integer sellValue;

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

    public Integer getSellValue() {
        return sellValue;
    }

    public void setSellValue(Integer sellValue) {
        this.sellValue = sellValue;
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

    public Sale() {
        this.crackers = new ArrayList<>();
    }

    public Sale(Customer customer, Integer sellValue) {
        this.customer = customer;
        this.sellValue = sellValue;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "customer=" + customer +
                ", sellValue=" + sellValue +
                '}';
    }
}
