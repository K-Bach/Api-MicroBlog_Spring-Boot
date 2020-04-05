/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.BlogUser;
import com.bachir.apimicroblog.entities.JsonResponseBody;
import com.bachir.apimicroblog.service.BlogUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class BlogUserController
{
    @Autowired
    BlogUserService blogUserService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns all the users")
    public ResponseEntity<JsonResponseBody> getUsers()
    {
        try
        {
            List<BlogUser> users = blogUserService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), users));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, params = {"username","password"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns a user by a Username and a Password")
    public ResponseEntity<JsonResponseBody> findUserByUsernameAndPassword(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {
        try
        {  
            Optional<BlogUser> user = blogUserService.findUserByUsernameAndPassword(username, password);
            if(user.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "User not found"));
            }
            else
                return ResponseEntity.status(HttpStatus.FOUND).body(new JsonResponseBody(HttpStatus.FOUND.value(), user.get()));
            
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns a user by an ID")
    public ResponseEntity<JsonResponseBody> getUserById(@ApiParam@PathVariable("id") Long userId)
    {
        try
        {
            BlogUser user = blogUserService.getBlogUserById(userId).get();
            return ResponseEntity.status(HttpStatus.FOUND).body(new JsonResponseBody(HttpStatus.FOUND.value(), user));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "adds a user into the database")
    public ResponseEntity<JsonResponseBody> addUser(HttpServletRequest request, @ApiParam@RequestBody BlogUser u)
    {
        try
        {   
            BlogUser user = blogUserService.insertUser(u);
            return ResponseEntity.status(HttpStatus.CREATED).header("location", request.getRequestURL().toString() + user.getId()).body(new JsonResponseBody(HttpStatus.CREATED.value(), null));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteuser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "deletes a user by an ID")
    public ResponseEntity<JsonResponseBody> deleteUserById(@ApiParam@PathVariable("id") Long userId)
    {
        try
        {   
            if( blogUserService.getBlogUserById(userId).isPresent())
            {
                blogUserService.deleteBlogUserById(userId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: user not found"));
            }
                
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
}
