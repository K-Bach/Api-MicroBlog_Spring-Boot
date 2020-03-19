/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.Post;
import com.bachir.apimicroblog.service.PostService;
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
    PostService postService;
    
    @RequestMapping
    public List<Post> getPosts()
    {
        return postService.getAllPosts();
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Post getPostById(@PathVariable("id") Long postId)
    {
        return postService.getPostById(postId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createPost(@RequestBody Post post)
    {
        postService.insertPost(post);
    }
    
    @RequestMapping(value = "/deletepost/{id}")
    public void deletePostById(@PathVariable("id") Long postId)
    {
        postService.deletePostById(postId);
    }
    
}
