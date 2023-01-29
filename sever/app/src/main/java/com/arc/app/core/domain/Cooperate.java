package com.arc.app.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_cooperate")
public class Cooperate extends MetaObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Integer status; // 1: Hop tac, 2: Ngung hop tac

    @Column(name = "type")
    private Integer type; // 1: Nha cung cap, 2: Nha van chuyen

    @Column(name = "address_detail")
    private String addressDetail;

    @ManyToOne
    @JoinColumn(name = "id_administrative")
    private Administrative address;

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

    public Administrative getAddress() {
        return address;
    }

    public void setAddress(Administrative address) {
        this.address = address;
    }
}
