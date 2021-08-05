package com.example.liquibase.mapper.impl;

import com.example.liquibase.dto.AuthorsDto;
import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.mapper.Mapper;
import com.example.liquibase.model.Books;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class BooksMapper implements Mapper<BooksResponseDto, Books> {
    @Override
    public BooksResponseDto toDto(Books books) {
        return BooksResponseDto
                .builder()
                .id(books.getId())
                .title(books.getTitle())
                .published(books.getPublished())
                .image(books.getImage())
                .description(books.getDescription())
                .available(books.getAvailable())
                .authors(books
                        .getAuthors()
                        .stream()
                .map(i -> AuthorsDto
                            .builder()
                            .id(i.getId())
                .name(i.getName())
                .surname(i.getSurname()).build()).collect(Collectors.toList()))
                .build();
    }
}
