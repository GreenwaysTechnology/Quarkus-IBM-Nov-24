package com.example.order;

import com.example.events.CreditReservedEvent;
import com.example.events.OrderCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class CustomerService {

    @Inject
    @Channel("credit-responses")
    Emitter<CreditReservedEvent> creditEventEmitter;

    @Incoming("orders")
    public void reserveCredit(OrderCreatedEvent event) {
        boolean creditReserved = checkCredit(event.customerId, event.amount);

        CreditReservedEvent responseEvent = new CreditReservedEvent();
        responseEvent.orderId = event.orderId;
        responseEvent.success = creditReserved;

        creditEventEmitter.send(responseEvent);
    }

    public boolean checkCredit(String customerId, double amount) {
        // Logic to check and reserve credit
        return amount <= 1000; // Example: Reserve credit if amount <= 1000
    }
}
