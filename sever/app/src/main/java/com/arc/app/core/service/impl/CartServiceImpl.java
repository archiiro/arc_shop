package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Cart;
import com.arc.app.core.domain.Product;
import com.arc.app.core.domain.User;
import com.arc.app.core.dto.CartDto;
import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.repository.CartRepository;
import com.arc.app.core.repository.ProductRepository;
import com.arc.app.core.repository.UserRepository;
import com.arc.app.core.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;

public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

//    0965269742

    @Override
    public CartDto getCartUser(Long id) {
        if(id != null) {
            Cart item = repository.getCartUser(id);
            if(item != null) {
                return new CartDto(item, true);
            }
        }
        return null;
    }

    @Override
    public CartDto save(CartDto dto) {
        if(dto == null) {
            return null;
        }
        Cart cart = new Cart();
        if(dto.getId() != null) {
            cart = repository.find(dto.getId());
        }
        if(cart == null) {
            cart = new Cart();
        }
        if(dto.getUser() != null && dto.getUser().getId() != null) {
            User user = userRepository.find(dto.getUser().getId());
            if(user != null) {
                cart.setUser(user);
            }
        }
        if(dto.getProducts() != null && dto.getProducts().size() > 0) {
            Iterator<ProductDto> iterator = dto.getProducts().iterator();
            HashSet<Product> products = new HashSet<Product>();
            while (iterator.hasNext()) {
                ProductDto productDto = iterator.next();
                Product product = null;
                if(productDto.getId() != null) {
                    product = productRepository.find(productDto.getId());
                }
                if(product != null) {
                    products.add(product);
                }
            }
            if(cart.getProducts() != null) {
                cart.getProducts().addAll(products);
            } else {
                cart.setProducts(products);
            }
        }
        cart = repository.save(cart);
        return new CartDto(cart, false);
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            Cart cart = repository.find(id);
            if(cart != null) {
                repository.delete(cart);
                return true;
            }
        }
        return false;
    }
}
