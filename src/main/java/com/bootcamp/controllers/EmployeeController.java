/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.controllers;

import com.bootcamp.entities.Employee;
import com.bootcamp.services.EmployeeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService service;

//    Success
    @GetMapping("")
    public Iterable<Employee> getAll() {
        return service.getAll();
    }

//    Success
    @PostMapping("")
    public Employee save(@Valid @RequestBody Employee employee) {
        return service.save(employee);
    }

//    success
    @GetMapping("{id}")
    public Employee getById(@PathVariable("id") String id) {
        return service.getById(id);
    }

//    Success
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") String id) {
        service.delete(id);
        return "Data "+id+" has been deleted";
    }
}
