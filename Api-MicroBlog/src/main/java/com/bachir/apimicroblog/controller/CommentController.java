/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.Comment;
import com.bachir.apimicroblog.entities.JsonResponseBody;
import com.bachir.apimicroblog.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/comments")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CommentController
{
    @Autowired
    CommentService commentService;
   
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns all the comments")
    public ResponseEntity<JsonResponseBody> getComments()
    {
        try
        {
            List<Comment> comments = commentService.getAllComments();
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), comments));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "returns a comment by an ID")
    public ResponseEntity<JsonResponseBody> getCommentById(@ApiParam@PathVariable("id") Long commentId)
    {
        try
        {
            
            Comment comment = commentService.getCommentById(commentId).get();
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), comment));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "adds a comment into the database")
    public ResponseEntity<JsonResponseBody> addComment(HttpServletRequest request, @ApiParam@RequestBody Comment c)
    {
        try
        {   
            Comment comment = commentService.insertComment(c);
            return ResponseEntity.status(HttpStatus.CREATED).header("location", request.getRequestURL().toString() + comment.getId()).body(new JsonResponseBody(HttpStatus.CREATED.value(), null));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/deletecomment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "deletes a comment by an ID")
    public ResponseEntity<JsonResponseBody> deleteCommentById(@ApiParam@PathVariable("id") Long commentId)
    {
        try
        {   
            if( commentService.getCommentById(commentId).isPresent())
            {
                commentService.deleteCommentById(commentId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: comment not found"));
            }
                
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
}
