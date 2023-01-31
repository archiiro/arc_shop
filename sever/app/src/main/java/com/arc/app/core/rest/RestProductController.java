package com.arc.app.core.rest;

import com.arc.app.core.dto.PersonDto;
import com.arc.app.core.dto.ProductCategoryDto;
import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class RestProductController {
    @Autowired
    private ProductService service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<ProductDto> getAll() {
        List<ProductDto> result = service.getAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDto find(@PathVariable("id") Long id) {
        ProductDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ProductDto save(@RequestBody ProductDto dto) {
        ProductDto result = service.save(dto);
        return result;
    }

    @RequestMapping(value = "/image-product/{id}", method = RequestMethod.POST)
    public ProductDto saveImageProduct(@PathVariable("id") Long id, @RequestParam("imageCard") List<MultipartFile> listFile) {
        ProductDto result = service.saveImageProduct(id, listFile);
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

    @RequestMapping(value = "/status/{id}", method = RequestMethod.DELETE)
    public void makeDelete(@PathVariable("id") Long id) {
        service.makeDelete(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<ProductDto> search(@RequestBody SearchDto dto) {
        Page<ProductDto> result = service.search(dto);
        return result;
    }
}
