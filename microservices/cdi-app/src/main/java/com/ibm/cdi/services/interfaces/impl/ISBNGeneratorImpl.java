package com.ibm.cdi.services.interfaces.impl;

import com.ibm.cdi.services.interfaces.NumberGenerator;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ISBNGeneratorImpl implements NumberGenerator {
    @Override
    public String generateISBNGenerator() {
        return "2323132123123213";
    }
}
