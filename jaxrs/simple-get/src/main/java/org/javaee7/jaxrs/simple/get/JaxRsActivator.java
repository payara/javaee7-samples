/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.get;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * This class activates JAX-RS and sets the base path to "/rest".
 *
 * @author Arjan Tijms
 *
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}
