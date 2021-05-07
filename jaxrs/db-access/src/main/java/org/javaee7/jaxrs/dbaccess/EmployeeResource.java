package org.javaee7.jaxrs.dbaccess;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * @author Arun Gupta
 */
@Path("employee")
@Stateless
public class EmployeeResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces("application/xml")
    public Employee[] get() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList().toArray(new Employee[0]);
    }
}
