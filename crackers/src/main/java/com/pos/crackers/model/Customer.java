package com.pos.crackers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMER",schema = "POS_DATA")
public class Customer extends AuditBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    Long id;

    @Column(name = "CUST_NAME")
    private String custName;

    @Column(name = "MOBILE_NUM")
    private String mobileNum;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addToSales(Sale sale) {
        this.sales.add(sale);
    }

    public Customer(){

    }

    public Customer(String custName, String mobileNum) {
        this.custName = custName;
        this.mobileNum = mobileNum;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custName='" + custName + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                '}';
    }
}