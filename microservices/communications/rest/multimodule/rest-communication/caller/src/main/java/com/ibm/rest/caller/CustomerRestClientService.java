package com.ibm.rest.caller;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "customers-api")
@Path("customers")
public interface CustomerRestClientService {
    //curd operation signature
    //api declarations
    @GET
    String findAll();

    @GET
    @Path("{id}")
    String findById(@PathParam("id") Long id);

    @POST
    String create(String payload);

    @PUT
    @Path("{id}")
    String update(@PathParam("id") Long id);

    @DELETE
    @Path("{id}")
    String remove(@PathParam("id") Long Id);
}
