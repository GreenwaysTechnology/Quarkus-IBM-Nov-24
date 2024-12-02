package com.ibm.reactive.dao.repository;

import com.ibm.reactive.dao.entity.Employee;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRespository implements PanacheRepository<Employee> {
}
