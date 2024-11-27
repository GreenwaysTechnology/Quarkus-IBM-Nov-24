package com.ibm.rest.api.payload;

import com.ibm.rest.api.payload.entity.Teacher;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("teacher")
public class TeachResource {

    @GET
    public String findAll() {
        return "All Teachers";
    }

    @POST
    public String save(Teacher teacher) {
        System.out.println(teacher);
        return "Teach Save Method is called";
    }
}
