package com.mjamsek.keycloak.common;

import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.models.KeycloakSession;

import javax.persistence.EntityManager;

public class PersistenceProducer {
    
    public static EntityManager getEntityManager(KeycloakSession session) {
        return session.getProvider(JpaConnectionProvider.class).getEntityManager();
    }
    
}
