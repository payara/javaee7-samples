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
import jakarta.servlet.http.HttpSession;

import org.javaee7.jaspic.ejbpropagation.ejb.PublicEJB;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/public/servlet-public-ejb-logout")
public class PublicServletPublicEJBLogout extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(PublicServletPublicEJBLogout.class.getName());

    @EJB
    private PublicEJB publicEJB;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
        }
        
        String ejbName = "";
        try {
            ejbName = publicEJB.getUserName();
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }

        request.logout();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        String webNameAfterLogout = null;
        if (request.getUserPrincipal() != null) {
            webNameAfterLogout = request.getUserPrincipal().getName();
        }

        String ejbNameAfterLogout = "";
        try {
            ejbNameAfterLogout = publicEJB.getUserName();
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }

        response.getWriter().write("web username: " + webName + "\n" + "EJB username: " + ejbName + "\n");
        response.getWriter().write("web username after logout: " + webNameAfterLogout + "\n" + "EJB username after logout: " + ejbNameAfterLogout + "\n");

    }

}
