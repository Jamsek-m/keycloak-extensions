package com.mjamsek.keycloak.rest;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class UserManagementProviderFactory implements RealmResourceProviderFactory {
    
    public static final String EXTENSION_ID = "user-management-ext";
    
    @Override
    public RealmResourceProvider create(KeycloakSession keycloakSession) {
        return new UserManagementProvider(keycloakSession);
    }
    
    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
    
    }
    
    @Override
    public void close() {
    
    }
    
    @Override
    public String getId() {
        return EXTENSION_ID;
    }
    
    @Override
    public void init(Config.Scope scope) {
    
    }
}
