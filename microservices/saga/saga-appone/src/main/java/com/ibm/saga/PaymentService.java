package com.ibm.saga;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class PaymentService {

    @Inject
    @Channel("payments-out")
    Emitter<Order> paymentEmitter;

    public Order processPayment(Order order) {
        try {
            // Simulate payment processing
            if (order.getQuantity() > 10) {
                throw new RuntimeException("Insufficient stock");
            }
            order.status = "COMPLETED";
            paymentEmitter.send(order);
        } catch (Exception e) {
            order.status = "FAILED";
            paymentEmitter.send(order);
            System.out.println("Payment failed for order: " + order.id);
        }
        return order;
    }

}

