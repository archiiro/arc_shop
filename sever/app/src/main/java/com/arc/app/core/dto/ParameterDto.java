package com.arc.app.core.dto;

import com.arc.app.core.domain.Parameter;

public class ParameterDto extends MetaObjectDto{
    private Long id;
    private Integer type;

    public ParameterDto() {

    }
    public ParameterDto(Parameter entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.type = entity.getType();
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.setDescription(entity.getDescription());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
