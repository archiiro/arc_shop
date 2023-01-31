package com.arc.app.core.service;

import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto find(Long id);

    ProductDto save(ProductDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    void makeDelete(Long id);

    ProductDto saveImageProduct(Long id, List<MultipartFile> listFile);

    Page<ProductDto> search(SearchDto dto);
}
