package com.ibm.cdi.services.interfaces.impl;

import com.ibm.cdi.services.interfaces.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("sql")
public class SQLRepositoryImpl implements Repository {
    @Override
    public String findAll() {
        return  "SQL Repository findAll";
    }
}
