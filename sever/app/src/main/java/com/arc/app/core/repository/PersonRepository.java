package com.arc.app.core.repository;

import com.arc.app.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("Select entity From Person entity Where entity.id = ?1")
    Person find(Long id);

    @Query("Select entity From Person entity Where entity.user.username = ?1 AND entity.user.password = ?2")
    Person findByUser(String username, String password);
}
