/** Copyright Payara Services Limited **/
package org.javaee7.jaxrs.simple.methods.filter;

public class ExceptionResponse {

    private String type;
    private String message;

    public ExceptionResponse() {}

    public ExceptionResponse(Exception ex) {
        this.type = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}