package com.ibm.service.communication.eventbus;


import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    //Listener
    @ConsumeEvent("greeting")
    public String consume(String name) {
        return name.toUpperCase();
    }

    @ConsumeEvent("sink")
    public void process(Message<String> message) {
        System.out.println("Address: " + message.address());
        System.out.println("Body " + message.body());
    }
}
