package com.arc.app.core.service;

import com.arc.app.core.dto.ProductCategoryDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDto> getAll();

    ProductCategoryDto find(Long id);

    ProductCategoryDto save(ProductCategoryDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    void makeDelete(Long id);

    Page<ProductCategoryDto> search(SearchDto dto);
}
