package com.example.liquibase.controller;

import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.service.AuthorService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/author")
@Log
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<BooksResponseDto>> getAllBook() {
        log.info("GET ALL AUTHORS");
        return new ResponseEntity(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<BooksResponseDto>> getAuthor(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("GET AUTHOR ID "+ id);
        return new ResponseEntity(authorService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("DELETE AUTHOR ID "+ id);
        authorService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
