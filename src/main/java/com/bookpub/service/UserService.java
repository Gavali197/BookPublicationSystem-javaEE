/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.service;

import com.bookpub.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author digit
 */
@Stateless
public class UserService {
    
    @PersistenceContext(unitName = "BookPU")
    private EntityManager em;

    // Register new user
    public User register(User user, String plainPassword) {
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
        user.setPasswordHash(hashed);
        em.persist(user);
        return user;
    }

    // Login verification
    public boolean verifyLogin(String username, String plainPassword) {
        User user = findByUsername(username);
        if (user == null) return false;
        return BCrypt.checkpw(plainPassword, user.getPasswordHash());
    }

    // Find by username
    public User findByUsername(String username) {
        List<User> list = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :u", User.class)
                .setParameter("u", username)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    // Get users by role
    public List<User> getUsersByRole(String role) {
        return em.createQuery(
                "SELECT u FROM User u WHERE u.role = :role", User.class)
                .setParameter("role", role)
                .getResultList();
    }
}
