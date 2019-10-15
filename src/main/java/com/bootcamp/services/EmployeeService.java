/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.services;

import com.bootcamp.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.repositories.EmployeeRepository;

/**
 *
 * @author DevidBa
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Iterable<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(String id) {
        return repository.findById(id).get();
    }

    public void delete(String id) {
        repository.delete(new Employee(id));
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }
}
