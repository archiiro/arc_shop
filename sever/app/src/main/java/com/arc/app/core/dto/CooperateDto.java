package com.arc.app.core.dto;

import com.arc.app.core.domain.Cooperate;

public class CooperateDto extends MetaObjectDto{
    private Long id;
    private Integer status;
    private Integer type;
    private String addressDetail;
    private AdministrativeDto address;

    public CooperateDto() {

    }
    public CooperateDto(Cooperate entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.status = entity.getStatus();
            this.type = entity.getType();
            this.addressDetail = entity.getAddressDetail();
            if(entity.getAddress() != null) {
                this.address = new AdministrativeDto(entity.getAddress(), true);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public AdministrativeDto getAddress() {
        return address;
    }

    public void setAddress(AdministrativeDto address) {
        this.address = address;
    }
}
