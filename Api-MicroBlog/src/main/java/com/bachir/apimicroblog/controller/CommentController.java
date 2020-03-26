/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.Comment;
import com.bachir.apimicroblog.service.CommentService;
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
@RequestMapping("/comments")
public class CommentController
{
    @Autowired
    CommentService commentService;
   
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "returns all the comments")
    public List<Comment> getComments()
    {
        return (List<Comment>) commentService.getAllComments();
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "returns a comment by an ID")
    public Comment getCommentById(@ApiParam@PathVariable("id") Long commentId)
    {
        return commentService.getCommentById(commentId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "adds a comment into the database")
    public void addComment(@ApiParam@RequestBody Comment comment)
    {
        commentService.insertComment(comment);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/deletecomment/{id}")
    @ApiOperation(value = "deletes a comment by an ID")
    public void deleteCommentById(@ApiParam@PathVariable("id") Long commentId)
    {
        commentService.deleteCommentById(commentId);
    }
}
