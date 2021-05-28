package com.mjamsek.keycloak.rest.services;

import com.mjamsek.keycloak.common.PersistenceProducer;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.models.jpa.UserAdapter;
import org.keycloak.models.jpa.entities.UserEntity;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class UserManagementService {
    
    public static List<UserRepresentation> queryUsersById(List<String> ids, KeycloakSession session) {
        EntityManager em = PersistenceProducer.getEntityManager(session);
        
        TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM UserEntity u WHERE u.id IN :ids", UserEntity.class);
        query.setParameter("ids", ids);
        
        return query.getResultStream().map(entity -> {
            UserModel model = new UserAdapter(session, session.getContext().getRealm(), em, entity);
            return ModelToRepresentation.toRepresentation(session, session.getContext().getRealm(), model);
        }).collect(Collectors.toList());
    }
    
    
}
