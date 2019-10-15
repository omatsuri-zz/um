/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.repositories;

import com.bootcamp.entities.AccountStatus;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DevidBa
 */
public interface AccountStatusRepository extends CrudRepository<AccountStatus, Integer>{
    
}
