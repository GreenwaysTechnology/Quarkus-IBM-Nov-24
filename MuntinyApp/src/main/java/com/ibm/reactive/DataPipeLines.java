package com.ibm.reactive;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class DataPipeLines {
    public static void main(String[] args) {
        System.out.println("start");
        Uni.createFrom().item("Hello").onItem().transform(item -> {
            return item.toUpperCase();
        }).onItem().delayIt().by(Duration.ofMillis(1000)).subscribe().with(System.out::println);
        System.out.println("end");
    }
}
