package org.javaee7.jaspic.dispatching.servlet;

import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javaee7.jaspic.dispatching.bean.MyBean;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/forwardedServlet")
public class ForwardedServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final static Logger logger = Logger.getLogger(ForwardedServlet.class.getName());
    
    @Inject
    private MyBean myBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("response from forwardedServlet - " + myBean.getText());
        response.getWriter().write("servletPath via Servlet - " + request.getServletPath() + "\n"); 
        try {
            response.getWriter().write("servletPath via CDI - " + myBean.getServletPath()); 
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }
    }

}