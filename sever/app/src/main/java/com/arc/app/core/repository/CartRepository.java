package com.arc.app.core.repository;

import com.arc.app.core.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Query("Select entity From Cart entity Where entity.user.id = ?1")
    Cart getCartUser(Long userId);

    @Query("Select entity From Cart entity Where entity.id = ?1")
    Cart find(Long userId);
}
