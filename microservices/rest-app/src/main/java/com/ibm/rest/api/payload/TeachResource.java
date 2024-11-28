package com.ibm.rest.api.payload;

import com.ibm.rest.api.payload.entity.Teacher;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("teachers")
public class TeachResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> findAll() {
        return List.of(new Teacher(1, "Subramanian", "Quarkus"),
                new Teacher(2, "Murugan", "Quarkus with Vertx"));
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher save(Teacher teacher) {
        System.out.println(teacher);
        return teacher;
    }

    @DELETE
    @Path("{id}")
    public void deleteTeacher(@PathParam("id") String id){
        System.out.println(id);

    }
}
