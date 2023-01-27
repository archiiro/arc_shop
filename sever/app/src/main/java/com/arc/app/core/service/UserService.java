package com.arc.app.core.service;

import com.arc.app.core.dto.RoleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto find(Long id);

    UserDto save(UserDto dto);

    Boolean isExist(String username);

    Boolean delete(Long id);

    UserDto login(String username, String password);

    Page<UserDto> search(SearchDto dto);
}
