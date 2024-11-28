package com.ibm.cdi.rest.api;

import com.ibm.cdi.services.interfaces.NumberGenerator;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("books")
public class BookResource {
    @Inject
    NumberGenerator numberGenerator;

    @GET
    public String getBookIsbn(){
        return numberGenerator.generateISBNGenerator();
    }

}
