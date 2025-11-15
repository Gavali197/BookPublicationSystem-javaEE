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
import com.bookpub.entity.Reviews;
import com.bookpub.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ReviewService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    public Reviews addReview(Book book, User admin, String comments, String status) {
        Reviews r = new Reviews();
        r.setBook(book);
        r.setAdmin(admin);
        r.setComments(comments);
        r.setStatus(status);
        em.persist(r);
        return r;
    }
}
