package com.arc.app.core.controller;

import com.arc.app.core.dto.AdministrativeDto;
import com.arc.app.core.dto.AdministrativeImport;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.other.ImportData;
import com.arc.app.core.service.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/ap/administrative")
public class RestAdministrativeController {
    @Autowired
    private AdministrativeService service;

    @RequestMapping(value = "/get-all-province", method = RequestMethod.GET)
    public List<AdministrativeDto> getAllProvince() {
        List<AdministrativeDto> result = service.getAllProvince();
        return result;
    }

    @RequestMapping(value = "/get-children/{parentId}", method = RequestMethod.GET)
    public List<AdministrativeDto> getChildren(@PathVariable("parentId") Long parentId) {
        List<AdministrativeDto> result = service.getChildrenByParent(parentId);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AdministrativeDto find(@PathVariable("id") Long id) {
        AdministrativeDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AdministrativeDto save(@RequestBody AdministrativeDto dto) {
        AdministrativeDto result = service.save(dto);
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
    public Page<AdministrativeDto> search(@RequestBody SearchDto dto) {
        Page<AdministrativeDto> result = service.search(dto);
        return result;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<?> importAdministrative(@RequestParam("upload") MultipartFile file) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
            List<AdministrativeImport> list = ImportData.getDataAdministrativeUnit(bis);
            if(list != null && list.size()>0) {
                service.importExcel(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
