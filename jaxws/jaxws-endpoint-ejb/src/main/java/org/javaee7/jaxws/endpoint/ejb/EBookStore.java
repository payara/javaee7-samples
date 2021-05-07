/** Copyright Payara Services Limited **/
package org.javaee7.jaxws.endpoint.ejb;

import static jakarta.jws.soap.SOAPBinding.Style.RPC;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

/**
 *
 * @author Fermin Gallego
 * @author Arjan Tijms
 *
 */
@WebService
@SOAPBinding(style = RPC)
public interface EBookStore {

    @WebMethod
    String welcomeMessage(String name);
}
