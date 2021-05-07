package org.javaee7.jaspic.jaccpropagation.sam;

import static jakarta.security.auth.message.AuthStatus.SUCCESS;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

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

/**
 * Very basic SAM that returns a single hardcoded user named "test" with role "architect" when the request parameter
 * <code>doLogin</code> is present.
 * 
 * @author Arjan Tijms
 * 
 */
public class TestServerAuthModule implements ServerAuthModule {

    private CallbackHandler handler;
    private Class<?>[] supportedMessageTypes = new Class[] { HttpServletRequest.class, HttpServletResponse.class };

    @Override
    public void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy, CallbackHandler handler,
        @SuppressWarnings("rawtypes") Map options) throws AuthException {
        this.handler = handler;
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject)
        throws AuthException {

        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();

        Callback[] callbacks;

        if (request.getParameter("doLogin") != null) {

            callbacks = new Callback[] { new CallerPrincipalCallback(clientSubject, "test"),
                new GroupPrincipalCallback(clientSubject, new String[] { "architect" }) };
        } else {

            // The JASPIC protocol for "do nothing"
            callbacks = new Callback[] { new CallerPrincipalCallback(clientSubject, (Principal) null) };
        }

        try {
            handler.handle(callbacks);
        } catch (IOException | UnsupportedCallbackException e) {
            throw (AuthException) new AuthException().initCause(e);
        }

        return SUCCESS;
    }

    @Override
    public Class<?>[] getSupportedMessageTypes() {
        return supportedMessageTypes;
    }

    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {
        return AuthStatus.SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {

    }
}