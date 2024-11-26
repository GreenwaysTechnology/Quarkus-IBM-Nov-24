package com.ibm.reactive;

import io.smallrye.mutiny.Uni;

public class HelloService {

    public Uni<String> sayHello() {
        return Uni.createFrom().item("Hello");
    }
}
