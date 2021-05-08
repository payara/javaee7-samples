package org.javaee7.jaspic.asyncauthentication.servlet;

import java.io.IOException;

import jakarta.ejb.EJB;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javaee7.jaspic.asyncauthentication.bean.AsyncBean;

/**
 * 
 * @author Arjan Tijms
 * 
 */
@WebServlet(urlPatterns = "/public/asyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private AsyncBean asyncBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(5000);

        asyncBean.doAsync(asyncContext);
    }

}