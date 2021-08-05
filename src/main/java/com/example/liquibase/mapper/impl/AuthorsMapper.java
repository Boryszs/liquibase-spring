package com.example.liquibase.mapper.impl;

import com.example.liquibase.dto.AuthorsResponseDto;
import com.example.liquibase.dto.BooksDto;
import com.example.liquibase.mapper.Mapper;
import com.example.liquibase.model.Authors;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorsMapper implements Mapper<AuthorsResponseDto, Authors> {

    @Override
    public AuthorsResponseDto toDto(Authors authors) {
        return AuthorsResponseDto.builder()
                .id(authors.getId())
                .name(authors.getName())
                .surname(authors.getSurname())
                .books(authors.getBooks()
                        .stream()
                        .map(i -> BooksDto
                                .builder()
                                .id(i.getId())
                                .title(i.getTitle())
                                .published(i.getPublished())
                                .image(i.getImage())
                                .description(i.getDescription())
                                .available(i.getAvailable())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }
}
