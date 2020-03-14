/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.dao.CommentDao;
import com.bachir.apimicroblog.domain.Comment;
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
    CommentDao commentDao;
   
    @RequestMapping
    public List<Comment> getUsers()
    {
        return (List<Comment>) commentDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment getCommentById(@PathVariable("id") Long commentId)
    {
        return commentDao.findById(commentId).orElseThrow();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createComment(@RequestBody Comment comment)
    {
        commentDao.save(comment);
    }
    
    @RequestMapping(value = "/deletecomment/{id}")
    public void deletePostById(@PathVariable("id") Long commentId)
    {
        commentDao.deleteById(commentId);
    }
}
