/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.controller;

/**
 *
 * @author digit
 */


import com.bookpub.entity.User;
import com.bookpub.service.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    
    private String username;
    private String password;
    private User loggedUser;

    @EJB
    private UserService userService;

    public String login() {
        if (userService.verifyLogin(username, password)) {
            loggedUser = userService.findByUsername(username);

            if (loggedUser.getRole().equals("ADMIN"))
                return "/admin/dashboard.xhtml?faces-redirect=true";
            else
                return "/author/dashboard.xhtml?faces-redirect=true";

        } else {
            System.out.println("Wrong login!");
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    // GETTERS & SETTERS
}
