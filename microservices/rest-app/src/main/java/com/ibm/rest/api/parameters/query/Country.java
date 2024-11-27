package com.ibm.rest.api.parameters.query;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("country")
public class Country {

    @GET
    @Path("by")
    public String getCountry(@QueryParam("language") @DefaultValue("eng") String language, @QueryParam("content") @DefaultValue("US") String contenent) {
        return "Country " + language + contenent;
    }
}
