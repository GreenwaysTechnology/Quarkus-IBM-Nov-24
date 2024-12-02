package com.example.order;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends PanacheEntity {
    @Column(name="customerId")
    public String customerId;
    @Column(name="amount")
    public double amount;
    @Column(name="status")
    public String status; // PENDING, APPROVED, REJECTED

}
