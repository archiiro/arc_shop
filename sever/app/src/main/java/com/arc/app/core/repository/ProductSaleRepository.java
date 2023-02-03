package com.arc.app.core.repository;

import com.arc.app.core.domain.ProductSale;
import com.arc.app.core.dto.ProductSaleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("Select new com.arc.app.core.dto.ProductSaleDto(entity, true) From ProductSale entity")
    List<ProductSaleDto> getAll();

    @Query("Select count(entity.id) From ProductSale entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From ProductSale entity Where entity.id = ?1")
    ProductSale find(Long id);
}
