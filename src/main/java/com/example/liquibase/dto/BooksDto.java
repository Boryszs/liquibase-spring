package com.example.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BooksDto {

    private Long id;

    private String title;

    private Integer published;

    private String image;

    private String description;

    private Boolean available;
}
