/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.service;

/**
 *
 * @author digit
 */

import com.bookpub.entity.ActivityLog;
import com.bookpub.entity.Book;
import com.bookpub.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ActivityLogService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    public void log(Book book, User user, String action) {
        ActivityLog log = new ActivityLog();
        log.setBook(book);
        log.setUser(user);
        log.setAction(action);
        em.persist(log);
    }
    
}
