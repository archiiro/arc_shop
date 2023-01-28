package com.arc.app.core.service.impl;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.dto.ImagePathDto;
import com.arc.app.core.repository.ImagePathRepository;
import com.arc.app.core.service.ImagePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ImagePathServiceImpl implements ImagePathService {
    @Autowired
    private ImagePathRepository repository;
    @Override
    public ImagePathDto save(ImagePathDto dto) {
        if(dto == null) {
            return null;
        }
        ImagePath imagePath = null;
        if(dto.getId() != null) {
            imagePath = repository.find(dto.getId());
        }
        if(imagePath == null) {
            imagePath = new ImagePath();
        }
        if(dto.getType() != null) {
            imagePath.setType(dto.getType());
        }
        if(dto.getSize() != null) {
            imagePath.setSize(dto.getSize());
        }
        if(dto.getName() != null) {
            imagePath.setName(dto.getName());
        }
        imagePath = repository.save(imagePath);
        return new ImagePathDto(imagePath, true);
    }
}
