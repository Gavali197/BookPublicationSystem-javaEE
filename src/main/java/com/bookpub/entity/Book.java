/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.entity;

import jakarta.persistence.*;
import com.bookpub.enums.BookStatus;
import java.time.LocalDateTime;

/**
 *
 * @author digit
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @PrePersist
    protected void onCreate() {
        submissionDate = LocalDateTime.now();
        status = BookStatus.PENDING;
    }
    //getter setter

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
