package com.ibm.reactive;

import io.smallrye.mutiny.Multi;

public class MultiFilteringAndSelection {
    public static void main(String[] args) {
        Multi.createFrom().items(1,2,2,3,4,3,9,92,23,23,11,10,87)
                //take only odd numbers
                .filter(item -> item % 2 != 0)
                //from the list of odd numbers take only first 10 items
                .select().first(10)
                //no duplicates
                .select().distinct()
                //transform those times multiply by 2
                .onItem().transform(item -> item * 2)
                .subscribe().with(System.out::println);
    }
}
