package com.arc.app.core.dto;


import com.arc.app.core.domain.Product;
import com.arc.app.core.domain.ProductCategory;

import java.util.Iterator;
import java.util.Set;

public class ProductCategoryDto extends MetaObjectDto{
    private Long id;

    private Integer status;
    private Set<ProductDto> products;

    public ProductCategoryDto() {

    }

    public ProductCategoryDto(ProductCategory entity, boolean isGetFull) {
        if(entity != null) {
            this.id = entity.getId();
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.status = entity.getStatus();
            if(isGetFull) {
                this.setDescription(entity.getDescription());
                if(entity.getProducts() != null && entity.getProducts().size() > 0) {
                    this.products.clear();
                    Iterator iterator = entity.getProducts().iterator();
                    while (iterator.hasNext()) {
                        Product product = (Product) iterator.next();
                        this.products.add(new ProductDto(product, false));
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
