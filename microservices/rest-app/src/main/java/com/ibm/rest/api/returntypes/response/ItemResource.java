package com.ibm.rest.api.returntypes.response;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    private List<Item> items = new ArrayList<>();
    private int currentId = 1;

    @GET
    public List<Item> getAllItems() {
        return items;
    }

    @POST
    public Response saveItem(Item item) {
        item.setId(currentId++);
        items.add(item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    //getBy id
    @GET
    @Path("{id}")
    public Response getItemById(@PathParam("id") int id) {
        Optional<Item> item = items.stream().filter(i -> i.getId() == id).findFirst();
        if (item.isPresent()) {
            return Response.ok().entity(item.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //update logic
    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") int id, Item updatedItem) {
        Optional<Item> item = items.stream().filter(i -> i.getId() == id).findFirst();
        if (item.isPresent()) {
            item.get().setName(updatedItem.getName());
            return Response.ok().entity(item.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //delete Logic
    @DELETE
    @Path("{id}")
    public Response removeItem(@PathParam("id") int id) {
        boolean removed = items.removeIf(item -> item.getId() == id);
        if (removed) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
