/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.entity;

/**
 *
 * @author digit
 */


import jakarta.persistence.*;

@Entity
@Table(name = "publishing")
public class Publishing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer printQuantity;

    private String printedDate;

    private Integer stock;

    private String onlinePublishDate;

    // GETTERS & SETTERS
}
