package com.borlok.crudrest.model;

public enum  Permit {
    AUTHORITY_READ("access:user"),
    AUTHORITY_WRITE("access:moderator"),
    AUTHORITY_ALL("access:admin");

    private String permit;

    Permit(String permit) {
        this.permit = permit;
    }

    public String getPermit() {
        return permit;
    }
}
