/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.controller;

import com.bachir.apimicroblog.domain.Post;
import com.bachir.apimicroblog.entities.JsonResponseBody;
import com.bachir.apimicroblog.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
@RequestMapping("/posts")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PostController
{

    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "returns all the posts")
    public ResponseEntity<JsonResponseBody> getPosts()
    {
        try
        {
            List<Post> posts = postService.getAllPosts();
            for( int i = 0; i < posts.size(); i++ )
            {
                posts.get(i).add(linkTo(methodOn(BlogUserController.class).getUserById(posts.get(i).getAutore().getId())).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), posts));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "returns a post by an ID")
    public ResponseEntity<JsonResponseBody> getPostById( @ApiParam @PathVariable("id") Long postId )
    {
        try
        {

            Post post = postService.getPostById(postId).get();
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), post));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: " + e.toString()));
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "adds a post in the database", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseBody> addPost( HttpServletRequest request, @ApiParam @RequestBody Post p )
    {
        try
        {
            Post post = postService.insertPost(p);
            return ResponseEntity.status(HttpStatus.CREATED).header("location", request.getRequestURL().toString() + post.getId()).body(new JsonResponseBody(HttpStatus.CREATED.value(), request.getRequestURL().toString() + post.getId()));
        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletepost/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "deletes a post by an ID")
    public ResponseEntity<JsonResponseBody> deletePostById( @ApiParam @PathVariable("id") Long postId )
    {
        try
        {
            if( postService.getPostById(postId).isPresent() )
            {
                postService.deletePostById(postId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Error: post not found"));
            }

        }
        catch( Exception e )
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }

}
