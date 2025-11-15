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
import com.bookpub.entity.Sales;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class SalesService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    public Sales createSalesRecord(Book book) {
        Sales s = new Sales();
        s.setBook(book);
        em.persist(s);
        return s;
    }

    public void updateSales(Book book, int sold, double royalty) {
        Sales s = em.createQuery(
            "SELECT s FROM Sales s WHERE s.book = :b", Sales.class)
            .setParameter("b", book)
            .getSingleResult();

        s.setSoldCopies(sold);
        s.setRoyaltyAmount(royalty);
        em.merge(s);
    }
}
