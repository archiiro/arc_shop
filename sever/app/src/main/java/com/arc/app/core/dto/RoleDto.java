package com.arc.app.core.dto;

import com.arc.app.core.domain.Role;

public class RoleDto {
    private Long id;

    private String code;
    private String name;

    public RoleDto() {

    }

    public RoleDto(Role entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
