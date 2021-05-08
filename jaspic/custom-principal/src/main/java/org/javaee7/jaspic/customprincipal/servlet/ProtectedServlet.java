package org.javaee7.jaspic.customprincipal.servlet;

import java.io.IOException;
import java.security.Principal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javaee7.jaspic.customprincipal.sam.MyPrincipal;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/protected/servlet")
public class ProtectedServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write("This is a protected servlet \n");

        String webName = null;
        boolean isCustomPrincipal = false;
        if (request.getUserPrincipal() != null) {
            Principal principal = request.getUserPrincipal();
            isCustomPrincipal = principal instanceof MyPrincipal; 
            webName = request.getUserPrincipal().getName();
        }

        boolean webHasRole = request.isUserInRole("architect");
        
        response.getWriter().write("isCustomPrincipal: " + isCustomPrincipal + "\n");
        response.getWriter().write("web username: " + webName + "\n");
        response.getWriter().write("web user has role \"architect\": " + webHasRole + "\n");

    }

}
