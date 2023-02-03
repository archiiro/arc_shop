package com.arc.app.core.service;

import com.arc.app.core.dto.ProductSaleDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductSaleService {
    List<ProductSaleDto> getAll();

    ProductSaleDto find(Long id);

    ProductSaleDto save(ProductSaleDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    ProductSaleDto saveImage(Long id, List<MultipartFile> listFile);

    Page<ProductSaleDto> search(SearchDto dto);
}
