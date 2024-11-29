package com.ibm.activerecord.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Customer extends PanacheEntity {
    //here all curd operations to be copied automatically
    @Column(name = "name")
    public String name;
    @Column(name = "city")
    public String city;
}
