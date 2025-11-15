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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPrintQuantity() {
        return printQuantity;
    }

    public void setPrintQuantity(Integer printQuantity) {
        this.printQuantity = printQuantity;
    }

    public String getPrintedDate() {
        return printedDate;
    }

    public void setPrintedDate(String printedDate) {
        this.printedDate = printedDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getOnlinePublishDate() {
        return onlinePublishDate;
    }

    public void setOnlinePublishDate(String onlinePublishDate) {
        this.onlinePublishDate = onlinePublishDate;
    }

    private String printedDate;

    private Integer stock;

    private String onlinePublishDate;

    // GETTERS & SETTERS
}
