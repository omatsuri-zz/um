/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.repositories;

import com.bootcamp.entities.Account;
import com.bootcamp.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DevidBa
 */
public interface AccountRepository extends CrudRepository<Account, String>{
//    @Query(value = "UPDATE tb_m_account SET status=?1 WHERE id=?2", nativeQuery = true)
//    public void updateStatus(int status, String id);
}
