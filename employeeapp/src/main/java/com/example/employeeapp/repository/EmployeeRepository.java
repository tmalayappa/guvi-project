package com.example.employeeapp.repository;

import com.example.employeeapp.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a Spring Data repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // MongoRepository provides methods like save(), findById(), findAll(), deleteById(), etc.
    // No custom methods are needed for the requirements.
}
