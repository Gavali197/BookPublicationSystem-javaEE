/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.service;

/**
 *
 * @author digit
 */

import com.bookpub.entity.Book;
import com.bookpub.entity.Publishing;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PublishingService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    public Publishing setupPublishing(Book book, int qty, String date, int stock) {
        Publishing p = new Publishing();
        p.setBook(book);
        p.setPrintQuantity(qty);
        p.setPrintedDate(date);
        p.setStock(stock);
        em.persist(p);
        return p;
    }
}
