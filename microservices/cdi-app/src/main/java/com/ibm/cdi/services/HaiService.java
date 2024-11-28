package com.ibm.cdi.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HaiService {

    public String sayHai() {
        return "Hai";
    }
}
