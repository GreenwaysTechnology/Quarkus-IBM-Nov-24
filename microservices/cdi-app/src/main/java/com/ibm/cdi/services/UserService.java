package com.ibm.cdi.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserService {
    public List<User> findAll() {
        return List.of(new User("user1", "password1"));
    }
}
