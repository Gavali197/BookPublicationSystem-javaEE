package com.bookpub.controller;

import com.bookpub.entity.User;
import com.bookpub.service.UserService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private User loggedUser;

    @EJB
    private UserService userService;

    // Login action
    public String login(){
        if (userService.verifyLogin(username, password)) {
            loggedUser = userService.findByUsername(username);

            // Put bean into HTTP session map so servlet filters can access it
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put("loginBean", this);

            if ("ADMIN".equalsIgnoreCase(loggedUser.getRole())) {
                return "/admin/dashboard.xhtml?faces-redirect=true";
            } else {
                return "/author/dashboard.xhtml?faces-redirect=true";
            }
        } else {
            // Add error message for invalid login
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Invalid username or password", null));
            return null; // Stay on login page
        }
    }

    // Logout action
public String logout() {
    loggedUser = null;
    username = null;
    password = null;

    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "/login.xhtml?faces-redirect=true";
}
    // Getters & Setters
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
}
