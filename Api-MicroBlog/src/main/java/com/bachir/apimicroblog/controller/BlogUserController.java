/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.dao.BlogUserDao;
import com.bachir.apimicroblog.domain.BlogUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("users")
public class BlogUserController
{
    @Autowired
    BlogUserDao blogUserDao;
    
    public List<BlogUser> getUsers()
    {
        return (List<BlogUser>) blogUserDao.findAll();
    }
}
