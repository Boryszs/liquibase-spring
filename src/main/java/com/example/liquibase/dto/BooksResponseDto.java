package com.example.liquibase.dto;

import com.example.liquibase.model.Authors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BooksResponseDto {

    private long id;

    private String title;

    private Integer published;

    private String image;

    private String description;

    private Boolean available;

    private List<AuthorsDto> authors;
}
