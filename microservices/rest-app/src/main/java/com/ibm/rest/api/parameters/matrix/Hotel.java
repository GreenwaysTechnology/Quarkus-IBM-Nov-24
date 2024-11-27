package com.ibm.rest.api.parameters.matrix;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;

@Path("hotel")
public class Hotel {

    @GET
    public String getHotelInfo(@MatrixParam("rating") @DefaultValue("0") String rating, @MatrixParam("location") @DefaultValue("Airport Near") String location, @MatrixParam("price") @DefaultValue("0.0") double price) {
        return "Rating=" + rating + " Location=" + location + " Price=" + price;
    }
}

