/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.dao;

import com.bachir.apimicroblog.domain.Comment;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Bachir_Karim
 */
public interface CommentDao extends CrudRepository<Comment, Long>
{

    public Optional<Comment> findCommentsByPostId( Long postId );
}
