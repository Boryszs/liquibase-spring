package com.example.liquibase.service;

import com.example.liquibase.dto.AuthorsResponseDto;
import javassist.NotFoundException;

import java.util.List;

public interface AuthorService {
    List<AuthorsResponseDto> findAll();

    AuthorsResponseDto findById(Long id) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;
}
