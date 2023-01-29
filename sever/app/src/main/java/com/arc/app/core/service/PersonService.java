package com.arc.app.core.service;

import com.arc.app.core.dto.PersonDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PersonService {
    PersonDto find(Long id);

    PersonDto save(PersonDto dto);

    Boolean delete(Long id);

    PersonDto saveAvatar(Long id, MultipartFile file);

    PersonDto saveImageCard(Long id, List<MultipartFile> listFile);

    void makeDelete(Long id);
}
