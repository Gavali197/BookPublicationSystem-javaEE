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
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer soldCopies;

    private Double royaltyAmount;

    private LocalDateTime lastUpdated;

    @PrePersist
    protected void onCreate() {
        soldCopies = 0;
        royaltyAmount = 0.0;
        lastUpdated = LocalDateTime.now();
    }

    // GETTERS & SETTERS
}
