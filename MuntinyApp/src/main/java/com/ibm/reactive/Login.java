package com.ibm.reactive;

import io.smallrye.mutiny.Uni;

class Auth {
    public static Uni auth(String name, String password) {
        if (name.equals("admin") && password.equals("admin")) {
            return Uni.createFrom().item("Login success");
        }
        return Uni.createFrom().failure(new RuntimeException("Login failed"));
    }
}

public class Login {
    public static void main(String[] args) {
        Auth.auth("admin", "admin").subscribe().with(System.out::println, System.err::println);
        Auth.auth("bar", "foo").subscribe().with(System.out::println, System.err::println);

    }
}
