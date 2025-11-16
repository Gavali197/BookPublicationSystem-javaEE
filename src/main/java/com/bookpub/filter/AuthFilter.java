/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookpub.filter;

/**
 *
 * @author digit
 */
import com.bookpub.controller.LoginBean;
import com.bookpub.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")


public class AuthFilter  implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        String url = request.getRequestURI();

        // Publicly allowed resources
        boolean loginPage = url.contains("login.xhtml");
        boolean resourceFile = url.contains("javax.faces.resource");

        if (loginPage || resourceFile) {
            chain.doFilter(req, res);
            return;
        }

        // Not logged in
        if (loginBean == null || loginBean.getLoggedUser() == null) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        User user = loginBean.getLoggedUser();

        // Admin trying to access author pages
        if (user.getRole().equals("ADMIN") && url.contains("/author/")) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.xhtml");
            return;
        }

        // Author trying to access admin pages
        if (user.getRole().equals("AUTHOR") && url.contains("/admin/")) {
            response.sendRedirect(request.getContextPath() + "/author/dashboard.xhtml");
            return;
        }

        chain.doFilter(req, res);
    }
}
