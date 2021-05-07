package org.javaee7.jaspic.ejbpropagation.ejb;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBContext;
import jakarta.ejb.Stateless;

/**
 * This is a "protected" EJB in the sense that there is role checking done prior to accessing (some) methods.
 * <p>
 * In JBoss EAP 6.1+ the use of any declarative security annotation switches the bean to a different mode, called "secured" in
 * JBoss terms.
 * <p>
 * GlassFish requires the <code>@DeclareRoles</code> annotation when programmatic role checking is done (making dynamic role
 * checking impossible).
 * 
 * @author Arjan Tijms
 */
@Stateless
//Required by GlassFish
@DeclareRoles({ "architect" })
//JBoss EAP 6.1+ defaults unchecked methods to DenyAll
@PermitAll
public class ProtectedEJB {

    @Resource
    private EJBContext ejbContext;

    @RolesAllowed("architect")
    public String getUserName() {
        try {
            return ejbContext.getCallerPrincipal() != null ? ejbContext.getCallerPrincipal().getName() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserArchitect() {
        try {
            return ejbContext.isCallerInRole("architect");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
