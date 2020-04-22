/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.service;

import com.bachir.apimicroblog.dao.CommentDao;
import com.bachir.apimicroblog.domain.Comment;
import com.bachir.apimicroblog.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Bachir_Karim
 */
@Service
public class CommentService
{
    @Autowired
    CommentDao commentDao;
    
    public Comment insertComment(Comment comment)
    {
        return commentDao.save(comment);
    }
    
    public void updateComment(Long id, Comment comment)
    {
        Comment exComment = commentDao.findById(id).get();
        exComment.setTesto( comment.getTesto() );
        
        commentDao.save(exComment);
    }
    
    public List<Comment> getAllComments()
    {
        return (List<Comment>) commentDao.findAll();
    }
    
    public Optional<Comment> getCommentById(Long id)
    {
        return commentDao.findById(id);
    }
    
    public Iterable<Comment> getCommentsByPost(Post post)
    {
        return commentDao.findByPost(post);
    }
    
    public void deleteCommentById(Long id)
    {
        commentDao.deleteById(id);
    }
}
