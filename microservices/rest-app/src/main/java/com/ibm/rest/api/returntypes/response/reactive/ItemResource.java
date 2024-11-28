package com.ibm.rest.api.returntypes.response.reactive;

import com.ibm.rest.api.returntypes.response.Item;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("reactiveitems")
public class ItemResource {
    private List<Item> items = new ArrayList<>();
    private int currentId = 1;

    //Return all items
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Item> getAllItems() {
        return Multi.createFrom().iterable(items);
    }

    @POST
    public Uni<Response> createItem(Item item) {
        return Uni.createFrom().item(() -> {
            item.setId(currentId++);
            items.add(item);
            return Response.status(Response.Status.CREATED).entity(item).build();
        });
    }

    @GET
    @Path("{id}")
    public Uni<Response> getItemById(@PathParam("id") int id) {
        return Uni.createFrom().item(() -> {
            Optional<Item> item = items.stream().filter(i -> i.getId() == id).findFirst();
            return item.map(i -> Response.ok(i).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
        });
    }

    @PUT
    @Path("{id}")
    public Uni<Response> updateItem(@PathParam("id") int id, Item updatedItem) {
        return Uni.createFrom().item(() -> {
            Optional<Item> item = items.stream().filter(i -> i.getId() == id).findFirst();
            if (item.isPresent()) {
                item.get().setName(updatedItem.getName());
                return Response.ok(item.get()).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        });

    }

    @DELETE
    @Path("{id}")
    public Uni<Response> deleteItem(@PathParam("id") int id) {
        return Uni.createFrom().item(() -> {
            boolean removedItem = items.removeIf(item -> item.getId() == id);
            if (removedItem) {
                return Response.noContent().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        });
    }

}
