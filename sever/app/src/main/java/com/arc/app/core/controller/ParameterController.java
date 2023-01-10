package com.arc.app.core.controller;

import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parameter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParameterController {
    @Autowired
    private ParameterService service;

    @RequestMapping(value = "/get-all/{type}", method = RequestMethod.GET)
    public List<ParameterDto> getAll(@PathVariable("type") Integer type) {
        List<ParameterDto> result = service.getAll(type);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ParameterDto find(@PathVariable("id") Long id) {
        ParameterDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ParameterDto save(@RequestBody ParameterDto dto) {
        ParameterDto result = service.save(dto, null);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ParameterDto save(@RequestBody ParameterDto dto, @PathVariable("id") Long id) {
        ParameterDto result = service.save(dto, id);
        return result;
    }

    @RequestMapping(value = "/exist", method = RequestMethod.PUT)
    public Boolean save(@RequestParam(value = "code", required = true) String code) {
        Boolean result = service.isExist(code);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("id") Long id) {
        Boolean result = service.delete(id);
        return result;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Page<ParameterDto> search(@RequestBody SearchDto dto) {
        Page<ParameterDto> result = service.search(dto);
        return result;
    }
}
