/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * @author Bachir_Karim
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class BlogUserNotFoundException extends RuntimeException
{ 
}
