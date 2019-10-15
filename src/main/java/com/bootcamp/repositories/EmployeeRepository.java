/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.repositories;

import com.bootcamp.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DevidBa
 */
public interface EmployeeRepository extends CrudRepository<Employee, String>{
    @Query(value = "select * from tb_m_employee WHERE email=?1", nativeQuery = true)
    public Employee getByEmail(String email);
    
    @Query(value = "select * from tb_m_employee WHERE id=(select id from tb_m_account WHERE token=?1)", nativeQuery = true)
    public Employee getByToken(String token);
}
