package com.ibm.reactive;
import io.smallrye.mutiny.Uni;

public class HelloMutiny {
    public static void main(String[] args) {
        Uni.createFrom().item("Hello").subscribe().with(data->{
            System.out.println("Hello");
        });
    }
}
