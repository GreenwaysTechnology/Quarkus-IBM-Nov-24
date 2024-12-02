package com.ibm.rest.caller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("customerproxy")
public class CustomerProxyResource {

    //inject interface
    @Inject
    @RestClient
    CustomerRestClientService client;

    @GET
    public String findAll(){
        return client.findAll();
    }

    @POST
    public String create(String payload){
        return  client.create(payload);
    }
    @GET
    @Path("{id}")
    public String findById(@PathParam("id") Long id){
        return  client.findById(id);
    }

    @PUT
    @Path("{id}")
    public String update(@PathParam("id") Long id){
        return client.update(id);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Long id){
        return client.remove(id);
    }

}
