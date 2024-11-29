package com.ibm.cdi.services.interfaces.impl;

import com.ibm.cdi.services.interfaces.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("nosql")
public class NoSQLRepositoryimpl implements Repository {
    @Override
    public String findAll() {
        return "NoSql Repository";
    }
}
