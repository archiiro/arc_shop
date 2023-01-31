package com.arc.app.core.rest;

import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.ProductCategoryDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-category")
public class RestProductCategoryController {
    @Autowired
    private ProductCategoryService service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<ProductCategoryDto> getAll() {
        List<ProductCategoryDto> result = service.getAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductCategoryDto find(@PathVariable("id") Long id) {
        ProductCategoryDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ProductCategoryDto save(@RequestBody ProductCategoryDto dto) {
        ProductCategoryDto result = service.save(dto);
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
    public void deleteFake(@PathVariable("id") Long id) {
        service.makeDelete(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<ProductCategoryDto> search(@RequestBody SearchDto dto) {
        Page<ProductCategoryDto> result = service.search(dto);
        return result;
    }
}
