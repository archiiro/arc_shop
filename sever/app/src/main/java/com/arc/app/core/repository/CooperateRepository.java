package com.arc.app.core.repository;

import com.arc.app.core.domain.Cooperate;
import com.arc.app.core.dto.CooperateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperateRepository extends JpaRepository<Cooperate, Long> {
    @Query("Select new com.arc.app.core.dto.CooperateDto(entity) From Cooperate entity Where entity.type = ?1")
    List<CooperateDto> getAll(Integer type);

    @Query("Select count(entity.id) From Cooperate entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From Cooperate entity Where entity.id = ?1")
    Cooperate find(Long id);
}
