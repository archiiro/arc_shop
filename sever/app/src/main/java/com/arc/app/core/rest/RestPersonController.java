package com.arc.app.core.rest;

import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.PersonDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ap/person")
public class RestPersonController {
    @Autowired
    private PersonService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonDto find(@PathVariable("id") Long id) {
        PersonDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PersonDto save(@RequestBody PersonDto dto) {
        PersonDto result = service.save(dto);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("id") Long id) {
        Boolean result = service.delete(id);
        return result;
    }

    @RequestMapping(value = "/avatar/{id}", method = RequestMethod.POST)
    public PersonDto saveAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile multipartFile) {
        PersonDto result = service.saveAvatar(id, multipartFile);
        return result;
    }

    @RequestMapping(value = "/avatar/{id}", method = RequestMethod.DELETE)
    public void makeDelete(@PathVariable("id") Long id) {
        service.makeDelete(id);
    }
}
