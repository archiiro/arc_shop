package com.arc.app.core.repository;

import com.arc.app.core.domain.Product;
import com.arc.app.core.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select new com.arc.app.core.dto.ProductDto(entity, true) From Product entity")
    List<ProductDto> getAll(Integer type);

    @Query("Select count(entity.id) From Product entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From Product entity Where entity.id = ?1")
    Product find(Long id);
}
