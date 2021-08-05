package com.example.liquibase.repository;

import com.example.liquibase.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors,Long> {
    boolean existsById(Long id);
    Optional<Authors> findById(Long id);
}
