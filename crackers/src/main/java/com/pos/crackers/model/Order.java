package com.pos.crackers.model;

import com.pos.crackers.entities.SaleRequest;
import com.pos.crackers.model.converter.SaleRequestJsonConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;


@Entity
@Table(name = "ORDER", schema = "POS_DATA")
public class Order extends AuditBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "order_pack", columnDefinition = "jsonb")
    @Convert(converter = SaleRequestJsonConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private SaleRequest orderPack;

    @Column(name = "FULFILLED")
    private Boolean fulfilled;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public SaleRequest getOrderPack() {
        return orderPack;
    }

    public void setOrderPack(SaleRequest orderPack) {
        this.orderPack = orderPack;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public Long getId(){
        return id;
    }
}
