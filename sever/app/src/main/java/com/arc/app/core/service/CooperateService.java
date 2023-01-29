package com.arc.app.core.service;

import com.arc.app.core.dto.CooperateDto;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CooperateService {
    List<CooperateDto> getAll(Integer type);

    CooperateDto find(Long id);

    CooperateDto save(CooperateDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    void makeDelete(Long id);

    Page<CooperateDto> search(SearchDto dto);
}
