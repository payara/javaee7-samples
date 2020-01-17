/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.methods.model;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResourceDAO {

    private List<Resource> resources = new ArrayList<>();

    public List<Resource> getAll() {
        return unmodifiableList(resources);
    }

    public boolean add(Resource resource) {
        return resources.add(resource);
    }

    public boolean remove(Resource resource) {
        return resources.remove(resource);
    }

}