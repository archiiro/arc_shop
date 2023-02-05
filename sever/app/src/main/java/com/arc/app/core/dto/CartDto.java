package com.arc.app.core.dto;


import com.arc.app.core.domain.Cart;
import com.arc.app.core.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long id;
    private UserDto user;
    private PersonDto person;
    private List<ProductDto> products;

    public CartDto() {
    }

    public CartDto(Cart entity, boolean getPerson) {
        if(entity != null) {
            this.id = entity.getId();
            if(entity.getUser() != null) {
                this.user = new UserDto(entity.getUser());
            }
            if(entity.getProducts() != null && entity.getProducts().size() > 0) {
                this.products = new ArrayList<>();
                for(Product item : entity.getProducts()) {
                    this.products.add(new ProductDto(item, false));
                }
            }
            if(getPerson) {
                this.person = new PersonDto(entity.getUser().getPerson());
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
