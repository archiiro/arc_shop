package com.arc.app.core.service;

import com.arc.app.core.dto.PersonDto;
import org.springframework.web.multipart.MultipartFile;


public interface PersonService {
    PersonDto find(Long id);

    PersonDto save(PersonDto dto);

    Boolean delete(Long id);

    PersonDto saveAvatar(Long id, MultipartFile multipartFile);

    void makeDelete(Long id);
}
