package com.arc.app.core.rest;

import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.dto.UserDto;
import com.arc.app.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap/user")
public class RestUserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public List<UserDto> getAll() {
        List<UserDto> result = service.getAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto find(@PathVariable("id") Long id) {
        UserDto result = service.find(id);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserDto save(@RequestBody UserDto dto) {
        UserDto result = service.save(dto);
        return result;
    }

    @RequestMapping(value = "/exist/{username}", method = RequestMethod.GET)
    public Boolean isExist(@PathVariable("username") String username) {
        Boolean result = service.isExist(username);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("id") Long id) {
        Boolean result = service.delete(id);
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password) {
        UserDto result = service.login(username, password);
        return result;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<UserDto> search(@RequestBody SearchDto dto) {
        Page<UserDto> result = service.search(dto);
        return result;
    }
}
