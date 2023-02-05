package com.arc.app.core.service;

import com.arc.app.core.dto.CartDto;

public interface CartService {
    CartDto getCartUser(Long id);

    CartDto save(CartDto dto);

    Boolean delete(Long id);
}
