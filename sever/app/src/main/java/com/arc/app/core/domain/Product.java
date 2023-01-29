package com.arc.app.core.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
public class Product extends MetaObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "status")
    private Integer status; // 1: Dang ban, 2: Dang het hang, 3: Ngung ban

    @ManyToOne
    @JoinColumn(name = "id_product_category")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<ImagePath> imageProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Set<ImagePath> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(Set<ImagePath> imageProducts) {
        this.imageProducts = imageProducts;
    }
}
