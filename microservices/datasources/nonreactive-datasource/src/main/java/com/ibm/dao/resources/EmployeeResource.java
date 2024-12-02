package com.ibm.dao.resources;

import com.ibm.dao.entity.Employee;
import com.ibm.dao.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("employees")
public class EmployeeResource {
    @Inject
    EmployeeRepository employeeRepository;

    @GET
    public List<Employee> findAll() {
        return employeeRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Employee findById(@PathParam("id") Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new WebApplicationException("Employee with ID of " + id + " not found");
        }
        return employee;
    }

    //post
    @POST
    @Transactional
    public Response create(Employee employee) {
        if (employee.name == null) {
            throw new WebApplicationException("Employee Creation Error");
        }
        employeeRepository.persist(employee);
        return Response.status(201).entity(employee).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Employee update(@PathParam("id") Long id, Employee employee) {
        if (employee.name == null || employee.city == null) {
            throw new WebApplicationException("Employee Name or city was not set on Request");
        }
        Employee employeeEntity = employeeRepository.findById(id);
        if (employeeEntity == null) {
            throw new WebApplicationException("Employee Not found");
        }
        //update logic
        employeeEntity.name = employee.name;
        employeeEntity.city = employee.city;
        return employeeEntity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Employee employeeEntity = employeeRepository.findById(id);
        if (employeeEntity == null) {
            throw new WebApplicationException("Employee Not Found");
        }
        employeeRepository.delete(employeeEntity);
        return Response.status(204).build();
    }
}
