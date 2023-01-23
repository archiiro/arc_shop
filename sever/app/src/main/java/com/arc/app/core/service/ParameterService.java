package com.arc.app.core.service;

import com.arc.app.core.dto.BaseObjectDto;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ParameterService {
    List<ParameterDto> getAll(Integer type);

    ParameterDto find(Long id);

    ParameterDto save(ParameterDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    Page<ParameterDto> search(SearchDto dto);

}
