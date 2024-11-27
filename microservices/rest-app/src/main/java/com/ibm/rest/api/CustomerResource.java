package com.ibm.rest.api;

import jakarta.ws.rs.*;

@Path("customers")
public class CustomerResource {

    @GET
    public String findAll() {
        return "Customers FindAll";
    }

    @GET
    @Path("reviews")
    public String getReviews(){
        return  "Customers Review";
    }
    @POST
    public String save() {
        return "Customers Save";
    }

    @PUT
    public String update() {
        return "Customers Update";
    }

    @DELETE
    public String delete() {
        return "Customers Delete";
    }
}
