package com.arc.app.core.repository;

import com.arc.app.core.domain.Parameter;
import com.arc.app.core.dto.ParameterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    @Query("Select new com.arc.app.core.dto.ParameterDto(entity) From Parameter entity Where entity.type = ?1")
    List<ParameterDto> getAll(Integer type);

    @Query("Select count(entity.id) From Parameter entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From Parameter entity Where entity.id = ?1")
    Parameter find(Long id);
}
