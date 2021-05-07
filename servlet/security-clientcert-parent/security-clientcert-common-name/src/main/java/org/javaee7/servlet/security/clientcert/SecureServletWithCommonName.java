/** Copyright Payara Services Limited **/
package org.javaee7.servlet.security.clientcert;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Cuba Stanley
 */
@WebServlet(urlPatterns = { "/SecureServletWithCommonName" })
public class SecureServletWithCommonName extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("principal common name " + request.getUserPrincipal().toString() + " in role g2:" + request.isUserInRole("g2"));
    }
}
