package com.ibm.cdi.rest.api;

import com.ibm.cdi.services.interfaces.Repository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("repository")
public class RepositoryResource {

    @Inject
    @Named("nosql")
    Repository repository;

    @GET
    public String findAll() {
        return repository.findAll();
    }
}
