package com.arc.app.core.dto;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.domain.Product;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

public class ProductDto extends MetaObjectDto{
    private Long id;
    private BigDecimal price;
    private Long quantity;
    private String unit;
    private Integer status;
    private ProductCategoryDto productCategory;
    private Set<ImagePathDto> imageProducts;

    public ProductDto() {

    }

    public ProductDto(Product entity, boolean isGetFull) {
        if(entity != null) {
            this.id = entity.getId();
            this.setCode(entity.getCode());
            this.setName(entity.getName());
            this.price = entity.getPrice();
            this.quantity = entity.getQuantity();
            this.unit = entity.getUnit();
            this.status = entity.getStatus();
            if(entity.getProductCategory() != null) {
                this.productCategory = new ProductCategoryDto(entity.getProductCategory(), false);
            }
            if(isGetFull) {
                this.setDescription(entity.getDescription());
            }
            Iterator iterator = entity.getImageProducts().iterator();
            while (iterator.hasNext()) {
                ImagePath image = (ImagePath) iterator.next();
                this.imageProducts.add(new ImagePathDto(image, false));
            }
        }
    }

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

    public ProductCategoryDto getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryDto productCategory) {
        this.productCategory = productCategory;
    }

    public Set<ImagePathDto> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(Set<ImagePathDto> imageProducts) {
        this.imageProducts = imageProducts;
    }
}
