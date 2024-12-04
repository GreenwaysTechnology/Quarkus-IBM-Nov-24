package com.ibm.saga;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/orders")
public class OrderResource {
    @Inject
    @Channel("orders-out")
    Emitter<Order> orderEmitter;

    @Inject
    PaymentService paymentService;

    @POST
    public Response placeOrder(Order order) {
        order.setStatus("PENDING");
        // Process the payment and update the order status
        Order updatedOrder = paymentService.processPayment(order);
        // Publish the updated order status to the "orders" topic
        orderEmitter.send(updatedOrder);
        return Response.ok(updatedOrder).build();
    }

}
