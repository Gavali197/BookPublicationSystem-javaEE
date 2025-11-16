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
import com.bookpub.service.ReviewService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminBookBean implements Serializable {

    @EJB
    private BookService bookService;

    @EJB
    private ReviewService reviewService;

    @Inject
    private LoginBean loginBean;

    private Book selectedBook;
    private String reviewComments;

    // Fetch books waiting for review
    public List<Book> getPendingBooks() {
        return bookService.getBooksByStatus(BookStatus.PENDING);
    }

    // Open review page
    public String review(Book book) {
        this.selectedBook = book;
        return "/admin/review.xhtml?faces-redirect=true";
    }

    // Approve book
    public String approve() {
        User admin = loginBean.getLoggedUser();
        reviewService.addReview(selectedBook, admin, reviewComments, "APPROVED");

        bookService.updateStatus(selectedBook, BookStatus.APPROVED);
        return "/admin/pending.xhtml?faces-redirect=true";
    }

    // Request changes
    public String requestChanges() {
        User admin = loginBean.getLoggedUser();
        reviewService.addReview(selectedBook, admin, reviewComments, "CHANGES_NEEDED");

        bookService.updateStatus(selectedBook, BookStatus.CHANGES_NEEDED);
        return "/admin/pending.xhtml?faces-redirect=true";
    }

    // GETTERS & SETTERS
    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }
}