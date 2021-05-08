package org.javaee7.jca.connector.simple.connector.outbound;

import jakarta.resource.ResourceException;
import jakarta.resource.cci.Connection;
import jakarta.resource.cci.ConnectionMetaData;
import jakarta.resource.cci.Interaction;
import jakarta.resource.cci.LocalTransaction;
import jakarta.resource.cci.ResultSetInfo;

/**
 *
 * @author arungup
 */
public class MyConnection implements Connection {

    private final MyManagedConnection myManagedConnection;

    public MyConnection(MyManagedConnection myManagedConnection) {
        this.myManagedConnection = myManagedConnection;
    }

    @Override
    public Interaction createInteraction() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalTransaction getLocalTransaction() throws ResourceException {
        return null;
    }

    @Override
    public ConnectionMetaData getMetaData() throws ResourceException {
        return new ConnectionMetaData() {
            @Override
            public String getEISProductName() {
                return "My Connector Platform";
            }

            @Override
            public String getEISProductVersion() {
                return "1.0";
            }

            @Override
            public String getUserName() {
                return "myUser";
            }
        };

    }

    @Override
    public ResultSetInfo getResultSetInfo() throws ResourceException {
        return null;
    }

    @Override
    public void close() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
