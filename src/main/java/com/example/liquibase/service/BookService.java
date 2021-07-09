package com.example.liquibase.service;

import com.example.liquibase.dto.BooksResponseDto;
import javassist.NotFoundException;

import java.util.List;

public interface BookService {
    List<BooksResponseDto> findAll();

    List<BooksResponseDto> findAllPagination(Long pageNo, Integer pageSize);

    BooksResponseDto findById(Long id) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    long count();
}
