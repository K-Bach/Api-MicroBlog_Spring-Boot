/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.dao;

import com.bachir.apimicroblog.domain.BlogUser;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Bachir_Karim
 */
public interface BlogUserDao extends CrudRepository<BlogUser, Long>
{
    
}
