package de.fraunhofer.abm.app.auth;

public interface Authorizer {

    public void requireRole(String role) throws SecurityException;
}
