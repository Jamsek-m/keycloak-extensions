package com.mjamsek.keycloak.common;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.JWSInputException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.representations.AccessToken;
import org.keycloak.services.managers.AppAuthManager;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;
import java.util.Set;

public class AdminAuth {
    
    public static final String ADMIN_CLIENT_ID = "realm-management";
    public static final String QUERY_USERS_ROLE = "query-users";
    public static final String VIEW_USERS_ROLE = "view-users";
    
    public static void requireQueryUsers(@NonNull KeycloakSession session) {
        checkAdminRole(session, QUERY_USERS_ROLE);
    }
    
    public static void requireViewUsers(@NonNull KeycloakSession session) {
        checkAdminRole(session, VIEW_USERS_ROLE);
    }
    
    private static void checkAdminRole(@NonNull KeycloakSession session, @NonNull String role) {
        String token = getToken(session).orElseThrow(ForbiddenException::new);
        AccessToken accessToken = getAccessToken(token);
        
        if (accessToken.getResourceAccess(ADMIN_CLIENT_ID) == null) {
            throw new ForbiddenException("Forbidden!");
        }
        
        Set<String> realmManagementRoles = accessToken.getResourceAccess(ADMIN_CLIENT_ID).getRoles();
        
        if (realmManagementRoles == null || realmManagementRoles.size() == 0) {
            throw new ForbiddenException("Forbidden!");
        }
        
        boolean hasRole = realmManagementRoles.stream().anyMatch(r -> r.equals(role));
        if (!hasRole) {
            throw new ForbiddenException("Forbidden!");
        }
    }
    
    private static AccessToken getAccessToken(@NonNull String token) {
        try {
            JWSInput input = new JWSInput(token);
            return input.readJsonContent(AccessToken.class);
        } catch (JWSInputException e) {
            throw new NotAuthorizedException("Bearer token format error!");
        }
    }
    
    private static Optional<String> getToken(KeycloakSession session) {
        return Optional.ofNullable(AppAuthManager.extractAuthorizationHeaderToken(session.getContext().getRequestHeaders()));
    }
}
