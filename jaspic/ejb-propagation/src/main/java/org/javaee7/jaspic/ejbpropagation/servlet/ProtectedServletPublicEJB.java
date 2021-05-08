package org.javaee7.jaspic.ejbpropagation.servlet;

import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javaee7.jaspic.ejbpropagation.ejb.PublicEJB;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/protected/servlet-public-ejb")
public class ProtectedServletPublicEJB extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ProtectedServletPublicEJB.class.getName());

    @EJB
    private PublicEJB publicEJB;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
        }

        String ejbName = publicEJB.getUserName();
        try {
            ejbName = publicEJB.getUserName();
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }

        response.getWriter().write("web username: " + webName + "\n" + "EJB username: " + ejbName + "\n");

    }

}
