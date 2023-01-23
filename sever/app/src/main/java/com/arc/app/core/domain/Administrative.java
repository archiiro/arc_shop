package com.arc.app.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "tbl_administrative")
@XmlRootElement
public class Administrative extends MetaObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;
    //1. Province
    //2. District
    //3. Commune

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Administrative parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Administrative> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Administrative getParent() {
        return parent;
    }

    public void setParent(Administrative parent) {
        this.parent = parent;
    }

    public Set<Administrative> getChildren() {
        return children;
    }

    public void setChildren(Set<Administrative> children) {
        this.children = children;
    }
}
