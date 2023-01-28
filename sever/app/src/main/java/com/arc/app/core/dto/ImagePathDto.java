package com.arc.app.core.dto;

import com.arc.app.core.domain.ImagePath;

public class ImagePathDto {
    private Long id;
    private String type;
    private Long size;
    private String name;

    public ImagePathDto() {

    }

    public ImagePathDto(ImagePath entity, boolean isGetFull) {
        if(entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            if(isGetFull) {
                this.type = entity.getType();
                this.size = entity.getSize();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
