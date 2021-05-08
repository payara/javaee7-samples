package org.javaee7.jaxws.endpoint;

import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 *
 * @author Fermin Gallego
 *
 */
@WebService
public interface EBookStore {

    @WebMethod
    String welcomeMessage(String name);

    @WebMethod
    List<String> findEBooks(String text);

    @WebMethod
    EBook takeBook(String title);

    @WebMethod
    void saveBook(EBook eBook);

    @WebMethod
    EBook addAppendix(EBook eBook, int appendixPages);
}
