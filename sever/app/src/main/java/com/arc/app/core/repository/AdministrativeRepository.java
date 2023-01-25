package com.arc.app.core.repository;

import com.arc.app.core.domain.Administrative;
import com.arc.app.core.dto.AdministrativeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrativeRepository extends JpaRepository<Administrative, Long> {
    @Query("Select new com.arc.app.core.dto.AdministrativeDto(entity, false) From Administrative entity Where entity.parent.id is NULL or entity.parent.id = 0 ")
    List<AdministrativeDto> getAllProvince();

    @Query("Select count(entity.id) From Administrative entity Where entity.code = ?1")
    Long count(String code);

    @Query("select entity From Administrative entity Where entity.id = ?1")
    Administrative find(Long id);

    @Query("Select entity From Administrative entity Where entity.code = ?1")
    List<Administrative> findCode(String code);

}
