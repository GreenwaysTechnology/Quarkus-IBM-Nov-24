package com.ibm.reactive;

import io.smallrye.mutiny.Multi;

public class MultiDataPipes {
    public static void main(String[] args) {
        Multi.createFrom().range(1, 100)
                .onItem().transform(item -> item * 2)
                .subscribe().with(System.out::println);
    }
}
