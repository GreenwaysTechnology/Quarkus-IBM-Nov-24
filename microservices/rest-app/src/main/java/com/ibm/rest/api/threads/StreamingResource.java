package com.ibm.rest.api.threads;

import com.ibm.rest.api.returntypes.response.Item;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Duration;
import java.util.List;

@Path("streaming")
public class StreamingResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Long> stream() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1));
    }

    //list of values
    @GET
    @Path("list")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Integer> list() {
        List list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return Multi.createFrom().iterable(list).onItem().call(i -> {
            Duration delay = Duration.ofSeconds(1);
            return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
        });
    }

    @GET
    @Path("items")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Item> listOfItems() {
        List list = List.of(new Item(1, "item1"), new Item(2, "Item 2"));
        return Multi.createFrom().iterable(list).onItem().call(i -> {
            Duration delay = Duration.ofSeconds(1);
            return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
        });
    }
}
