package com.mjamsek.keycloak.logs;

import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class LoggingProvider implements EventListenerProvider {
    
    private static final Logger LOG = Logger.getLogger(LoggingProvider.class.getName());
    
    @Override
    public void onEvent(Event event) {
        LOG.info("Event: " + toString(event));
    }
    
    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        LOG.info("Admin event: " + adminEvent.getOperationType());
    }
    
    @Override
    public void close() {
    
    }
    
    private String toString(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ type: ");
        sb.append(event.getType());
        sb.append(", realm: ");
        sb.append(event.getRealmId());
        sb.append(", clientId: ");
        sb.append(event.getClientId());
        sb.append(", userId: ");
        sb.append(event.getUserId());
        sb.append(", ipAddress: ");
        sb.append(event.getIpAddress());
        
        if (event.getError() != null) {
            sb.append(", error: ");
            sb.append(event.getError());
        }
        
        if (event.getDetails() != null) {
            event.getDetails().forEach((key, value) -> {
                sb.append(", ");
                sb.append(key);
                sb.append(": ");
                sb.append(value);
            });
        }
        sb.append(" }");
        
        return sb.toString();
    }
}
