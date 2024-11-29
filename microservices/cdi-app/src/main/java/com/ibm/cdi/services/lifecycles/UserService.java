package com.ibm.cdi.services.lifecycles;

import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.util.List;

@ApplicationScoped
public class UserService {
    List<String> users;

    @PostConstruct
    public void init() {
        users = List.of("Subramanian", "Ram");
    }

    public void start(@Observes StartupEvent event) {
        System.out.println("Startup");
    }

    public List<String> findAll() {
        return users;
    }
}
