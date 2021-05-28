package com.mjamsek.keycloak.rest.resources;

import com.mjamsek.keycloak.common.AdminAuth;
import com.mjamsek.keycloak.rest.models.IdQueryRequest;
import com.mjamsek.keycloak.rest.services.UserManagementService;
import org.keycloak.models.KeycloakSession;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class UserManagementResource {
    
    private final KeycloakSession session;
    
    public UserManagementResource(KeycloakSession session) {
        this.session = session;
    }
    
    @POST
    @Path("/query-ids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response queryUsersById(IdQueryRequest request) {
        AdminAuth.requireQueryUsers(session);
        
        if (request.getIds() == null) {
            throw new BadRequestException("Fields 'ids' missing!");
        }
        
        if (request.getIds().size() == 0) {
            return Response.ok(new ArrayList<>()).build();
        }
        
        List<UserRepresentation> users = UserManagementService.queryUsersById(request.getIds(), session);
        return Response.ok(users).build();
    }
    
}
