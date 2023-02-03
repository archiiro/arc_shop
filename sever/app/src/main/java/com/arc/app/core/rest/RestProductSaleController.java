package com.arc.app.core.rest;

import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.dto.ProductSaleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product_sale")
public class RestProductSaleController {
    @Autowired
    private ProductSaleService service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<ProductSaleDto> getAll() {
        List<ProductSaleDto> result = service.getAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductSaleDto find(@PathVariable("id") Long id) {
        ProductSaleDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ProductSaleDto save(@RequestBody ProductSaleDto dto) {
        ProductSaleDto result = service.save(dto);
        return result;
    }

    @RequestMapping(value = "/image-product-sale/{id}", method = RequestMethod.POST)
    public ProductSaleDto saveImage(@PathVariable("id") Long id, @RequestParam("image") List<MultipartFile> listFile) {
        ProductSaleDto result = service.saveImage(id, listFile);
        return result;
    }

    @RequestMapping(value = "/exist/{code}", method = RequestMethod.GET)
    public Boolean isExist(@PathVariable("code") String code) {
        Boolean result = service.isExist(code);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("id") Long id) {
        Boolean result = service.delete(id);
        return result;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<ProductSaleDto> search(@RequestBody SearchDto dto) {
        Page<ProductSaleDto> result = service.search(dto);
        return result;
    }
}
