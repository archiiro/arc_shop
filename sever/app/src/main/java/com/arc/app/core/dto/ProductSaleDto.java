package com.arc.app.core.dto;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.domain.ProductSale;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class ProductSaleDto {
    private Long id;
    private String code;
    private Double sale;
    private Date startDate;
    private Date endDate;
    private Set<ImagePathDto> imageProductSales;

    public ProductSaleDto() {

    }

    public ProductSaleDto(ProductSale entity, boolean isGetFull) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.sale = entity.getSale();
            this.startDate = entity.getStartDate();
            this.endDate = entity.getEndDate();
            if(isGetFull) {
                Iterator iterator = entity.getImageProductSales().iterator();
                while (iterator.hasNext()) {
                    ImagePath image = (ImagePath) iterator.next();
                    this.imageProductSales.add(new ImagePathDto(image, false));
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

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<ImagePathDto> getImageProductSales() {
        return imageProductSales;
    }

    public void setImageProductSales(Set<ImagePathDto> imageProductSales) {
        this.imageProductSales = imageProductSales;
    }
}
