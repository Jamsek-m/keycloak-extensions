package com.mjamsek.keycloak.logs;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class LoggingProviderFactory implements EventListenerProviderFactory {
    
    public static final String EXTENSION_ID = "simple-logger";
    
    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new LoggingProvider();
    }
    
    @Override
    public void init(Config.Scope config) {
    
    }
    
    @Override
    public void postInit(KeycloakSessionFactory factory) {
    
    }
    
    @Override
    public void close() {
    
    }
    
    @Override
    public String getId() {
        return EXTENSION_ID;
    }
}
