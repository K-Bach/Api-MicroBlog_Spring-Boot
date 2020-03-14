/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.dao.PostDao;
import com.bachir.apimicroblog.domain.Post;
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
@RequestMapping("/posts")
public class PostController
{
    @Autowired
    PostDao postDao;
    
    @RequestMapping
    public List<Post> getPosts()
    {
        return (List<Post>) postDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Post getPostById(@PathVariable("id") Long postId)
    {
        return postDao.findById(postId).orElseThrow();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createPost(@RequestBody Post post)
    {
        postDao.save(post);
    }
    
    @RequestMapping(value = "/deletepost/{id}")
    public void deletePostById(@PathVariable("id") Long postId)
    {
        postDao.deleteById(postId);
    }
    
}
