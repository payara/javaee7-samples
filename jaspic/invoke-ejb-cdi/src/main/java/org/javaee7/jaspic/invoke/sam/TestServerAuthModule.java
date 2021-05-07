package org.javaee7.jaspic.invoke.sam;

import static java.util.logging.Level.SEVERE;
import static jakarta.security.auth.message.AuthStatus.SEND_SUCCESS;
import static jakarta.security.auth.message.AuthStatus.SUCCESS;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.enterprise.inject.spi.CDI;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import jakarta.security.auth.message.AuthException;
import jakarta.security.auth.message.AuthStatus;
import jakarta.security.auth.message.MessageInfo;
import jakarta.security.auth.message.MessagePolicy;
import jakarta.security.auth.message.callback.CallerPrincipalCallback;
import jakarta.security.auth.message.callback.GroupPrincipalCallback;
import jakarta.security.auth.message.module.ServerAuthModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javaee7.jaspic.invoke.bean.CDIBean;
import org.javaee7.jaspic.invoke.bean.EJBBean;

/**
 * 
 * @author Arjan Tijms
 * 
 */
public class TestServerAuthModule implements ServerAuthModule {

    private final static Logger logger = Logger.getLogger(TestServerAuthModule.class.getName());
    
    private CallbackHandler handler;
    private Class<?>[] supportedMessageTypes = new Class[] { HttpServletRequest.class, HttpServletResponse.class };
    
    

    @Override
    public void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy, CallbackHandler handler,
        @SuppressWarnings("rawtypes") Map options) throws AuthException {
        this.handler = handler;
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject) throws AuthException {
        
        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();
        HttpServletResponse response = (HttpServletResponse) messageInfo.getResponseMessage();
        
        if ("cdi".equals(request.getParameter("tech"))) {
            callCDIBean(request, response, "validateRequest");
        } else  if ("ejb".equals(request.getParameter("tech"))) {
            callEJBBean(response, "validateRequest");
        }
        
        try {
            handler.handle(new Callback[] {
                new CallerPrincipalCallback(clientSubject, "test"),
                new GroupPrincipalCallback(clientSubject, new String[] { "architect" })
            });
            
            return SUCCESS;
            
        } catch (IOException | UnsupportedCallbackException e) {
            throw (AuthException) new AuthException().initCause(e);
        }
    }

    @Override
    public Class<?>[] getSupportedMessageTypes() {
        return supportedMessageTypes;
    }

    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {

        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();
        HttpServletResponse response = (HttpServletResponse) messageInfo.getResponseMessage();

        if ("cdi".equals(request.getParameter("tech"))) {
            callCDIBean(request, response, "secureResponse");
        } else if ("ejb".equals(request.getParameter("tech"))) {
            callEJBBean(response, "secureResponse");
        }

        return SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {
        
        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();
        HttpServletResponse response = (HttpServletResponse) messageInfo.getResponseMessage();

        if ("cdi".equals(request.getParameter("tech"))) {
            callCDIBean(request, response, "cleanSubject");
        } else if ("ejb".equals(request.getParameter("tech"))) {
            callEJBBean(response, "cleanSubject");
        }
    }
    
    private void callCDIBean(HttpServletRequest request, HttpServletResponse response, String phase) {
        try {
            CDIBean cdiBean = CDI.current().select(CDIBean.class).get();
            response.getWriter().write(phase + ": " + cdiBean.getText() + "\n");
            
            cdiBean.setTextViaInjectedRequest();
            
            response.getWriter().write(phase + ": " + request.getAttribute("text")+ "\n");
            
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }
    }
    
    private void callEJBBean(HttpServletResponse response, String phase) {
        try {
            EJBBean ejbBean = (EJBBean) new InitialContext().lookup("java:module/EJBBean");
            response.getWriter().write(phase + ": " + ejbBean.getText() + "\n");
        } catch (Exception e) {
            logger.log(SEVERE, "", e);
        }
    }
    
    
}