package com.example.order;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Customer extends PanacheEntity {
    public double creditLimit;

    public Customer() {
    }

    public Customer(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
