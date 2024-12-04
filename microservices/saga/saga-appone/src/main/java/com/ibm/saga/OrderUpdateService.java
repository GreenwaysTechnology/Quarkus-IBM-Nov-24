package com.ibm.saga;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
public class OrderUpdateService {
    @Incoming("payments-in")
    @Transactional
    public void updateOrderStatus(Order order) {
        Order existingOrder = Order.findById(order.id);
        if (existingOrder != null) {
            existingOrder.status = order.status;
            existingOrder.persist();
            System.out.println("Order status updated to " + order.status + " for order: " + order.id);
        } else {
            System.out.println("Order not found for ID: " + order.id);
        }
    }

}
