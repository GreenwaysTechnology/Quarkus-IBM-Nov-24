package com.ibm.reactive.dao.resources;

import com.ibm.reactive.dao.entity.Employee;
import com.ibm.reactive.dao.repository.EmployeeRespository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("employees")
public class EmployeeResource {
    @Inject
    EmployeeRespository employeeRespository;

    @GET
    public Uni<List<Employee>> findAll() {
        return employeeRespository.listAll();
    }

    @Path("{id}")
    @GET
    public Uni<Response> findById(@PathParam("id") Long id) {
        return employeeRespository.findById(id).onItem().transform(entity -> {
            if (entity == null) {
                throw new WebApplicationException("Employee Not Found");
            }
            return Response.ok(entity).build();
        });
    }

    @POST
    @WithTransaction
    public Uni<Response> create(Employee employee) {
        if (employee.name == null || employee == null) {
            throw new WebApplicationException("Employee Not Found");
        }
        return employeeRespository.persist(employee).onItem().transform(entity -> {
            return Response.status(201).entity(entity).build();
        });
    }

    @PUT
    @WithTransaction
    @Path("{id}")
    public Uni<Response> update(@PathParam("id") Long id, Employee employee) {
        if (employee == null) {
            throw new WebApplicationException("Employee Not Found");
        }
        //update logic
        String query = "city='" + employee.getCity() + "' WHERE id = ?1";
        System.out.println(query);
        return employeeRespository.update(query, id).onItem().transform(entity -> Response.ok().entity(entity).build());
    }

    //delete
    @DELETE
    @WithTransaction
    @Path("{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return employeeRespository.deleteById(id).onItem().transform(isDeleted ->
                isDeleted ? Response.ok().status(Response.Status.NO_CONTENT).build()
                        : Response.ok().status(Response.Status.NOT_FOUND).build()
        );
    }
}
