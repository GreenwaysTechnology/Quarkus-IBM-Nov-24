package com.ibm.saga;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


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
            order.setStatus("COMPLETED");

            System.out.println("Payment processed successfully for order: " + order.getOrderId());
        } catch (Exception e) {
            order.setStatus("FAILED");
            System.out.println("Payment failed for order: " + order.getOrderId());
        }
        return order;
    }

}
