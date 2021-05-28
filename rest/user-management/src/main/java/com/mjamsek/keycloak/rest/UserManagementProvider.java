package com.mjamsek.keycloak.rest;

import com.mjamsek.keycloak.rest.resources.UserManagementResource;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public class UserManagementProvider implements RealmResourceProvider {
    
    private final KeycloakSession session;
    
    UserManagementProvider(KeycloakSession session) {
        this.session = session;
    }
    
    @Override
    public Object getResource() {
        return new UserManagementResource(session);
    }
    
    @Override
    public void close() {
    
    }
}
