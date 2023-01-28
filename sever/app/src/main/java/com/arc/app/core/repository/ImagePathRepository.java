package com.arc.app.core.repository;

import com.arc.app.core.domain.ImagePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePathRepository extends JpaRepository<ImagePath, Long> {
    @Query("Select entity From ImagePath entity Where entity.id = ?1")
    ImagePath find(Long id);
}
