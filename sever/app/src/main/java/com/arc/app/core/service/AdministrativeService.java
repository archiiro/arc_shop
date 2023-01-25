package com.arc.app.core.service;

import com.arc.app.core.dto.AdministrativeDto;
import com.arc.app.core.dto.AdministrativeImport;
import com.arc.app.core.dto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdministrativeService {
    List<AdministrativeDto> getAllProvince();

    List<AdministrativeDto> getChildrenByParent(Long parentId);

    AdministrativeDto find(Long id);

    AdministrativeDto save(AdministrativeDto dto);

    Boolean isExist(String code);

    Boolean delete(Long id);

    Page<AdministrativeDto> search(SearchDto dto);

    List<Long> getListChildrenId(Long parentId);

    Boolean deleteAll(Long id);

    void importExcel(List<AdministrativeImport> listDto);
}
