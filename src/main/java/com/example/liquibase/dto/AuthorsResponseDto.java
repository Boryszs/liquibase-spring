package com.example.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorsResponseDto {

    private long id;

    private String name;

    private String surname;

    private List<BooksDto> books;
}
