package com.mjamsek.keycloak.rest.models;

import java.util.List;

public class IdQueryRequest {
    
    private List<String> ids;
    
    public List<String> getIds() {
        return ids;
    }
    
    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
