/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.controller;

/**
 *
 * @author digit
 */


import com.bookpub.entity.Book;
import com.bookpub.entity.User;
import com.bookpub.enums.BookStatus;
import com.bookpub.service.BookService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AuthorBookBean implements Serializable {
    
    @EJB
    private BookService bookService;

    @Inject
    private LoginBean loginBean;

    private Book newBook = new Book();

    public String createBook() {
        newBook.setAuthor(loginBean.getLoggedUser());
        newBook.setStatus(BookStatus.PENDING);
        bookService.createBook(newBook);
        newBook = new Book();
        return "/author/books.xhtml?faces-redirect=true";
    }

    public List<Book> getMyBooks() {
        User author = loginBean.getLoggedUser();
        return bookService.getBooksByAuthor(author);
    }

    // GETTERS & SETTERS

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }
}
