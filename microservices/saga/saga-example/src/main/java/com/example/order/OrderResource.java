package com.example.order;


import com.example.events.CreditReservedEvent;
import com.example.events.OrderCreatedEvent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class OrderResource {

    @Inject
    @Channel("orders-out")
    Emitter<OrderCreatedEvent> orderEventEmitter;

    @GET
    public List<Order> findOrders() {
        return Order.listAll();
    }

    @POST
    @Transactional
    public Response createOrder(Order order) {
        order.status = "PENDING";
        order.persist();

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.orderId = order.id.toString();
        event.customerId = order.customerId;
        event.amount = order.amount;

        orderEventEmitter.send(event);
        return Response.status(201).entity(event).build();
    }

    @Incoming("credit-responses")
    @Transactional
    public void handleCreditResponse(CreditReservedEvent event) {
        Order order = Order.findById(Long.parseLong(event.orderId));
        System.out.println();
        if (order != null) {
            order.status = event.success ? "APPROVED" : "REJECTED";
            order.persist();
        }
    }
}
