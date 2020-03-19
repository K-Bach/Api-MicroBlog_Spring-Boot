/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.service;

import com.bachir.apimicroblog.dao.PostDao;
import com.bachir.apimicroblog.domain.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Bachir_Karim
 */
@Service
public class PostService
{
    @Autowired
    PostDao postDao;
    
    public void insertPost(Post post)
    {
        postDao.save(post);
    }
    
    public List<Post> getAllPosts()
    {
        return (List<Post>) postDao.findAll();
    }
    
    public Post getPostById(Long id)
    {
        return postDao.findById(id).get();
    }
    
    public void deletePostById(Long id)
    {
        postDao.deleteById(id);
    } 
}
