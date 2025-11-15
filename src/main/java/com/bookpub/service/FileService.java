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
import com.bookpub.entity.BookFiles;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Stateless
public class FileService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    private static final String BASE_PATH = "C:/book_files/";

    public BookFiles uploadFile(Book book, Part file, String type) throws Exception {

        // Create directories if not present
        new File(BASE_PATH + type.toLowerCase()).mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getSubmittedFileName();
        File dest = new File(BASE_PATH + type.toLowerCase() + "/" + fileName);

        // Save physical file
        Files.copy(file.getInputStream(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Save DB record
        BookFiles bf = new BookFiles();
        bf.setBook(book);
        bf.setFileType(type);
        bf.setFilePath(dest.getAbsolutePath());

        em.persist(bf);

        return bf;
    }
}
