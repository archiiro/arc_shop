package com.arc.app.core.controller;

import com.arc.app.core.dto.RoleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap/role")
public class RestRoleController {
    @Autowired
    private RoleService service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<RoleDto> getAll() {
        List<RoleDto> result = service.getAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleDto find(@PathVariable("id") Long id) {
        RoleDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RoleDto save(@RequestBody RoleDto dto) {
        RoleDto result = service.save(dto);
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
    public Page<RoleDto> search(@RequestBody SearchDto dto) {
        Page<RoleDto> result = service.search(dto);
        return result;
    }
}
