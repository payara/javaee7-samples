package org.javaee7.jacc.contexts.servlet;

import java.io.IOException;

import jakarta.security.jacc.PolicyContext;
import jakarta.security.jacc.PolicyContextException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/requestServlet")
public class RequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("jaccTest", "true");

        try {
            HttpServletRequest requestFromPolicy = (HttpServletRequest) PolicyContext.getContext("jakarta.servlet.http.HttpServletRequest");

            if (requestFromPolicy != null) {
                response.getWriter().print("Obtained request from context.");

                if ("true".equals(requestFromPolicy.getAttribute("jaccTest"))) {
                    response.getWriter().print("Attribute present in request from context.");
                }

                if ("true".equals(requestFromPolicy.getParameter("jacc_test"))) {
                    response.getWriter().print("Request parameter present in request from context.");
                }

            }
        } catch (PolicyContextException e) {
            e.printStackTrace(response.getWriter());
        }

    }

}
