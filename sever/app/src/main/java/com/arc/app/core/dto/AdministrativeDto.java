package com.arc.app.core.dto;

import com.arc.app.core.domain.Administrative;

import java.util.*;

public class AdministrativeDto {
    private Long id;
    private String code;
    private String name;
    private Integer level;
    private AdministrativeDto parent;
    private Set<AdministrativeDto> children;

    private String province;

    private String district;

    private String commune;

    public AdministrativeDto() {

    }

    public AdministrativeDto(Administrative entity) {
        if(entity.getParent() == null  && entity.getLevel() == 1) {
            this.province = entity.getName();
        }
        if(entity.getParent() != null && entity.getLevel() == 2) {
            this.district = entity.getName();
        }
        if(entity.getParent().getParent() != null && entity.getLevel() == 3) {
            this.commune = entity.getName();
        }
    }

    public AdministrativeDto(Administrative entity, boolean includeChild) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.level = entity.getLevel();
            if(entity.getParent() != null) {
                this.parent = new AdministrativeDto(entity.getParent(), false);
            }
            if(includeChild) {
                Iterator iterator;
                if(entity.getChildren() != null && entity.getChildren().size() > 0) {
                    this.children.clear();
                    iterator = entity.getChildren().iterator();
                    while (iterator.hasNext()) {
                        Administrative adm = (Administrative) iterator.next();
                        this.children.add(new AdministrativeDto(adm, false));
                    }
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AdministrativeDto getParent() {
        return parent;
    }

    public void setParent(AdministrativeDto parent) {
        this.parent = parent;
    }

    public Set<AdministrativeDto> getChildren() {
        return children;
    }

    public void setChildren(Set<AdministrativeDto> children) {
        this.children = children;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
}
