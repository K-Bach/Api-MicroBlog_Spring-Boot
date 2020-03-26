/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.BlogUser;
import com.bachir.apimicroblog.service.BlogUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "returns all the users")
    public List<BlogUser> getUsers()
    {
        return blogUserService.getAllUsers();
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns a user by an ID")
    public BlogUser getUserById(@ApiParam@PathVariable("id") Long userId)
    {
        return blogUserService.getBlogUserById(userId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "adds a user into the database")
    public void addUser(@ApiParam@RequestBody BlogUser user)
    {
        blogUserService.insertUser(user);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteuser/{id}")
    @ApiOperation(value = "deletes a user by an ID")
    public void deleteUserById(@ApiParam@PathVariable("id") Long userId)
    {
        blogUserService.deleteBlogUserById(userId);
    }
}
