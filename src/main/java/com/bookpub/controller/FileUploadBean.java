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
import com.bookpub.entity.BookFiles;
import com.bookpub.service.FileService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.servlet.http.Part;

@Named
@RequestScoped
public class FileUploadBean implements Serializable {

    private Part file;
    private Book selectedBook;
    private String fileType; // MANUSCRIPT, REVISED, FINAL_PDF, COVER

    @EJB
    private FileService fileService;

    public String uploadFile() {

        if (file == null || selectedBook == null) {
            System.out.println("Upload failed: Missing file or book!");
            return null;
        }

        try {
            fileService.uploadFile(selectedBook, file, fileType);
            System.out.println("File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "books.xhtml?faces-redirect=true";
    }

    // GETTERS / SETTERS

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
