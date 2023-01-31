package com.arc.app.core.repository;

import com.arc.app.core.domain.ProductCategory;
import com.arc.app.core.dto.ProductCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("Select new com.arc.app.core.dto.ProductCategoryDto(entity, false) From ProductCategory entity")
    List<ProductCategoryDto> getAll();

    @Query("Select count(entity.id) From ProductCategory entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From ProductCategory entity Where entity.id = ?1")
    ProductCategory find(Long id);
}
