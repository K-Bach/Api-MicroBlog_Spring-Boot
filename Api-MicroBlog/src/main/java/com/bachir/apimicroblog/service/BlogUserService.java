/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.service;

import com.bachir.apimicroblog.dao.BlogUserDao;
import com.bachir.apimicroblog.domain.BlogUser;
import java.util.List;
import java.util.Optional;
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

    public BlogUser insertUser( BlogUser blogUser )
    {
        return blogUserDao.save(blogUser);
    }

    public Optional<BlogUser> getUserByUsernameAndPassword( String username, String password ) throws Exception
    {
        if( "".equals(username) )
        {
            throw new Exception("Invalid username");
        }
        else if( "".equals(password) )
        {
            throw new Exception("Invalid password");
        }
        else
        {
            Optional<BlogUser> user = blogUserDao.findByUsernameAndPassword(username, password);
            return user;
        }
    }

    public List<BlogUser> getAllUsers()
    {
        return (List<BlogUser>) blogUserDao.findAll();
    }

    public Optional<BlogUser> getBlogUserById( Long id ) throws Exception
    {
        if( "".equals(id.toString()) )
        {
            throw new Exception("Invalid id");
        }
        else
        {
            Optional<BlogUser> user = blogUserDao.findById(id);
            if( user.isEmpty() )
            {
                throw new Exception("User not found");
            }
            else
            {
                return user;
            }
        }
    }

    public void deleteBlogUserById( Long id ) throws Exception
    {
        if( "".equals(id.toString()) )
        {
            throw new Exception("Invalid id");
        }
        else
        {
            Optional<BlogUser> user = this.getBlogUserById(id);
            if( user.isEmpty() )
            {
                throw new Exception("User not found");
            }
            else
            {
                blogUserDao.deleteById(id);
            }
        }

    }

}
