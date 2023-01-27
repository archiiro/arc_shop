package com.arc.app.core.dto;

import com.arc.app.core.domain.Role;
import com.arc.app.core.domain.User;

import java.util.Iterator;
import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Set<RoleDto> roles;

    public UserDto() {

    }

    public UserDto(User entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.username = entity.getUsername();
            this.password = entity.getPassword();
            if(entity.getRoles() != null && entity.getRoles().size() > 0) {
                this.roles.clear();
                Iterator iterator = entity.getRoles().iterator();
                while (iterator.hasNext()) {
                    Role role = (Role) iterator.next();
                    this.roles.add(new RoleDto(role));
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
