package com.arc.app.core.repository;

import com.arc.app.core.domain.User;
import com.arc.app.core.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select new com.arc.app.core.dto.UserDto(entity) From User entity")
    List<UserDto> getAll();

    @Query("Select count(entity.id) From User entity Where entity.username = ?1")
    Long count(String username);

    @Query("Select entity From User entity Where entity.id = ?1")
    User find(Long id);

    @Query("Select entity From User entity Where entity.username = ?1 AND entity.password = ?2")
    User login(String username, String password);
}
