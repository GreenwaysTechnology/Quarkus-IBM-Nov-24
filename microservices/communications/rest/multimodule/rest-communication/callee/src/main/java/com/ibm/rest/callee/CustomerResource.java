package com.ibm.rest.callee;

import jakarta.ws.rs.*;

@Path("customers")
public class CustomerResource {
    //curd operations
    @GET
    public String findAll() {
        return "FindAll Customers";
    }

    @Path("{id}")
    @GET
    public String findById(@PathParam("id") Long id) {
        return "Find By Id " + id;
    }

    @POST
    public String create(String payload) {
        return "Saved  " + payload;
    }

    @PUT
    @Path("{id}")
    public String update(@PathParam("id") Long id) {
        return "Update By id " + id;
    }

    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Long id) {
        return "Remove By id " + id;
    }

}
