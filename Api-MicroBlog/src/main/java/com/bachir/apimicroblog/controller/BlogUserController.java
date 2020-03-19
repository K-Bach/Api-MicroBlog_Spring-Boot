/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.BlogUser;
import com.bachir.apimicroblog.service.BlogUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/users")
public class BlogUserController
{
    @Autowired
    BlogUserService blogUserService;
    
    @RequestMapping
    public List<BlogUser> getUsers()
    {
        return blogUserService.getAllUsers();
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogUser getUserById(@PathVariable("id") Long userId)
    {
        return blogUserService.getBlogUserById(userId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody BlogUser user)
    {
        blogUserService.insertUser(user);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteuser/{id}")
    public void deleteUserById(@PathVariable("id") Long userId)
    {
        blogUserService.deleteBlogUserById(userId);
    }
}
