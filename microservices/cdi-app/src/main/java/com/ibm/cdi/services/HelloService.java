package com.ibm.cdi.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String sayHello() {
        return "Hello";
    }
}
