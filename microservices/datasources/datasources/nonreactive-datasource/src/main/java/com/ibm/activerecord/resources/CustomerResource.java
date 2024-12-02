package com.ibm.activerecord.resources;

import com.ibm.activerecord.entity.Customer;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("customers")
public class CustomerResource {

    @GET
    public List<Customer> findAll() {
        return Customer.listAll();
    }

    @GET
    @Path("{id}")
    public Customer findById(@PathParam("id") Integer id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            throw new WebApplicationException("Customer With ID of " + id + " does not exits");
        }
        return customer;
    }

    //insert
    @POST
    @Transactional
    public Response create(Customer customer) {
        if (customer.id != null) {
            throw new WebApplicationException("Customer not set", 422);
        }
        customer.persist();
        return Response.ok(customer).status(201).build();
    }

    //update
    @PUT
    @Path("{id}")
    @Transactional
    public Customer update(@PathParam("id") Integer id, Customer customer) {
        //test the customer is available
        Customer customerEntity = Customer.findById(id);
        if (customerEntity == null) {
            throw new WebApplicationException("Customer with id of" + id + "Does not exits");
        }
        if (customer.name == null) {
            throw new WebApplicationException("Customer Payload does not exits");
        }
        //update logic
        customerEntity.name = customer.name;
        customerEntity.city = customer.city;
        return customerEntity;
    }

    //delete
    @DELETE
    @Path("{id}")
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        Customer customerEntity = Customer.findById(id);
        if (customerEntity == null) {
            throw new WebApplicationException("Customer with id of" + id + "Does not exits");
        }
        //delete
        customerEntity.delete();
        return Response.status(204).build();
    }

}
