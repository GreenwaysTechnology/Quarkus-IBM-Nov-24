package com.ibm.reactive;

import io.smallrye.mutiny.Multi;

import java.util.List;

public class CollectionEvents {
    public static void main(String[] args) {
        List mylist = List.of(1, 2, 3, 4, 5, 6, 8, 9, 10);
        //Stream vs Collection
        //Stream is flow of data from one place to another place while flowing data you can apply
        //data processing operations like transformation,filtering,modifying,merging .
        //you create stream ?from where you can create stream -
        // from collections,future,hardcoded data,from existing java Stream
        Multi.createFrom().iterable(mylist).subscribe().with(System.out::println);
    }
}
