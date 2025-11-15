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
import com.bookpub.entity.User;
import com.bookpub.enums.BookStatus;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    // Create book (author uploads manuscript)
    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }

    // Update book status
    public void updateStatus(Book book, BookStatus status) {
        book.setStatus(status);
        em.merge(book);
    }

    // Get author books
    public List<Book> getBooksByAuthor(User author) {
        return em.createQuery(
              "SELECT b FROM Book b WHERE b.author = :a", Book.class)
              .setParameter("a", author)
              .getResultList();
    }

    // Admin: Get books by status
    public List<Book> getBooksByStatus(BookStatus status) {
        return em.createQuery(
              "SELECT b FROM Book b WHERE b.status = :s", Book.class)
              .setParameter("s", status)
              .getResultList();
    }

    // Find book by ID
    public Book find(int id) {
        return em.find(Book.class, id);
    }
}
