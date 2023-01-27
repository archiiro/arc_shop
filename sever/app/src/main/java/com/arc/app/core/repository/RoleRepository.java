package com.arc.app.core.repository;

import com.arc.app.core.domain.Role;
import com.arc.app.core.dto.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("Select new com.arc.app.core.dto.RoleDto(entity) From Role entity")
    List<RoleDto> getAll();

    @Query("Select count(entity.id) From Role entity Where entity.code = ?1")
    Long count(String code);

    @Query("Select entity From Role entity Where entity.id = ?1")
    Role find(Long id);
}
