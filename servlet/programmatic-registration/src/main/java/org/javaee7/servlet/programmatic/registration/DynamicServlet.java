package org.javaee7.servlet.programmatic.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author OrelGenya
 */
public class DynamicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("dynamic GET");
    }

    @Override
    public String getServletInfo() {
        return "My dynamic awesome servlet!";
    }
}