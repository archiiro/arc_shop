package com.arc.app.core.dto;

import com.arc.app.core.domain.Parameter;

public class ParameterDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer type;

    public ParameterDto() {

    }

    public ParameterDto(Parameter entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.type = entity.getType();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
