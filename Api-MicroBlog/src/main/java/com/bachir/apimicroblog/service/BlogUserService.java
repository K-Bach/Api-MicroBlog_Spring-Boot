/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.service;

import com.bachir.apimicroblog.dao.BlogUserDao;
import com.bachir.apimicroblog.domain.BlogUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Bachir_Karim
 */
@Service
public class BlogUserService
{
    @Autowired
    BlogUserDao blogUserDao;
    
    public void insertUser(BlogUser blogUser)
    {
        blogUserDao.save(blogUser);
    }
    
    public List<BlogUser> getAllUsers()
    {
        return (List<BlogUser>) blogUserDao.findAll();
    }
    
    public BlogUser getBlogUserById(Long id)
    {
        return blogUserDao.findById(id).get();
    }
    
    public void deleteBlogUserById(Long id)
    {
        blogUserDao.deleteById(id);
    } 
    
}
