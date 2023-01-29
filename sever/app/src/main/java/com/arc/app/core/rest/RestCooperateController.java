package com.arc.app.core.rest;

import com.arc.app.core.dto.CooperateDto;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.CooperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cooperate")
public class RestCooperateController {
    @Autowired
    private CooperateService service;

    @RequestMapping(value = "/get-all/{type}", method = RequestMethod.GET)
    public List<CooperateDto> getAll(@PathVariable("type") Integer type) {
        List<CooperateDto> result = service.getAll(type);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CooperateDto find(@PathVariable("id") Long id) {
        CooperateDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CooperateDto save(@RequestBody CooperateDto dto) {
        CooperateDto result = service.save(dto);
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
    public Page<CooperateDto> search(@RequestBody SearchDto dto) {
        Page<CooperateDto> result = service.search(dto);
        return result;
    }
}
