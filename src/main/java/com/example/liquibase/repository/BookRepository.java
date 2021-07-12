package com.example.liquibase.repository;

import com.example.liquibase.model.Authors;
import com.example.liquibase.model.Books;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Long> {

    @Transient
    @Query(value = "select a from authors a where a.id = :id")
    Optional<Authors> findAuthor(@Param("id") Long id);
}
