package com.arc.app.core.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "tbl_product_category")
@XmlRootElement
public class ProductCategory extends MetaObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
