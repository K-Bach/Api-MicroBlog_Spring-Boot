/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bachir.apimicroblog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Data;


/**
 *
 * @author Bachir_Karim
 */
@Entity
@Data
@Table(name = "posts")
public class Post implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String titolo;

    @ManyToOne
    private BlogUser autore;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataOra;

    @Basic
    private String testo;
    
}
