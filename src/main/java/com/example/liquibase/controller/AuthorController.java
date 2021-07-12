package com.example.liquibase.controller;

import com.example.liquibase.dto.AuthorsDto;
import com.example.liquibase.dto.AuthorsResponseDto;
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
    public ResponseEntity<List<AuthorsResponseDto>> getAllBook() {
        log.info("GET ALL AUTHORS");
        return new ResponseEntity(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AuthorsResponseDto>> getBookId(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("GET AUTHOR ID "+ id);
        return new ResponseEntity(authorService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("DELETE AUTHOR ID "+ id);
        authorService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AuthorsDto authorsDto) {
        log.info("ADD AUTHOR "+ authorsDto);
        authorService.save(authorsDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/author-book")
    public ResponseEntity saveAuthorOnBook(@RequestBody AuthorsResponseDto authorsResponseDto) {
        log.info("ADD AUTHOR WITH BOOK"+ authorsResponseDto);
        authorService.save(authorsResponseDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
