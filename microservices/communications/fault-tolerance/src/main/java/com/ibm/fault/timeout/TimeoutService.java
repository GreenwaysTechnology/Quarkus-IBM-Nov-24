package com.ibm.fault.timeout;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TimeoutService {

    public List<String> getProducts() throws InterruptedException {
        //Simulate Delay
        Thread.sleep(3000);
        return List.of("Product1", "Product2");
    }
}
