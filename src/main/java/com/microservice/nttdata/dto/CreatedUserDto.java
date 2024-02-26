package com.microservice.nttdata.dto;

import java.util.Date;

public class CreatedUserDto {
    private String id;
    private String name;
    private String email;
    private String token;
    private Boolean isActive;
    private Date created;
    private Date modified;
    private Date lastLogin;

    public CreatedUserDto(String id, String name, String email, Boolean isActive, Date created, Date lastLogin, Date modified, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }
}
