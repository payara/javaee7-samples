// Copyright [2019] [Payara Foundation and/or its affiliates]
package org.javaee7.jaxrs.ejb.lookup.iface.war;

import java.rmi.RemoteException;

import jakarta.ejb.EJBMetaData;
import jakarta.ejb.Handle;
import jakarta.ejb.HomeHandle;
import jakarta.ejb.RemoveException;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * jakarta.ejb.* classes are not allowed as business interfaces, but the bean still can be mapped.
 *
 * @author David Matejcek
 */
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Stateless
@Path("illegal-interface")
public class IllegalInterfaceBean implements jakarta.ejb.EJBHome {

    @Override
    public void remove(Handle handle) throws RemoteException, RemoveException {
    }


    @Override
    public void remove(Object primaryKey) throws RemoteException, RemoveException {
    }


    @Path("ejbMetaData")
    @GET
    @Override
    public EJBMetaData getEJBMetaData() throws RemoteException {
        return null;
    }


    @Override
    public HomeHandle getHomeHandle() throws RemoteException {
        return null;
    }
}
