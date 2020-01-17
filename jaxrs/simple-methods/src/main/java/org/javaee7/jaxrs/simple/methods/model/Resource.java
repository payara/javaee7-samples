/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.methods.model;

import java.util.Objects;

public class Resource {

    private String name;

    public Resource() {
    }

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Resource))
            return false;
        Resource other = (Resource) obj;
        return Objects.equals(name, other.name);
    }
    
}