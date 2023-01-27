package com.arc.app.core.service;

import com.arc.app.core.dto.RoleDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto find(Long id);

    RoleDto save(RoleDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    Page<RoleDto> search(SearchDto dto);
}
