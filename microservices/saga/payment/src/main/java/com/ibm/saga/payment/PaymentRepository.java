package com.ibm.saga.payment;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
}