/** Copyright Payara Services Limited **/
package org.javaee7.jaxws.endpoint.ejb;

import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

/**
 *
 * @author Fermin Gallego
 * @author Arjan Tijms
 *
 */
@Stateless
@WebService(endpointInterface = "org.javaee7.jaxws.endpoint.ejb.EBookStore")
public class EBookStoreImpl implements EBookStore {

    @Override
    public String welcomeMessage(String name) {
        return "Welcome to EBookStore WebService, Mr/Mrs " + name;
    }

}
