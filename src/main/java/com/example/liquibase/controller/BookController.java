package com.example.liquibase.controller;

import com.example.liquibase.dto.BooksDto;
import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.service.BookService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/book")
@Log
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BooksResponseDto>> getAllBook() {
        log.info("GET ALL BOOKS");
        return new ResponseEntity(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<BooksResponseDto>> getAllBookPagination(@RequestParam(value ="page", defaultValue = "0") Long page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("GET ALL BOOKS PAGINATION");
        return new ResponseEntity(bookService.findAllPagination(page,size), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        log.info("GET ALL BOOKS PAGINATION");
        return new ResponseEntity(bookService.count(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksDto> getBook(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("GET BOOK TITLE "+ id);
        return new ResponseEntity(bookService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBook(@PathVariable(value = "id") Long id) throws NotFoundException {
        log.info("DELETE BOOK ID "+ id);
        bookService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity addBookToAuthor(@PathVariable(value = "id") Long id,@RequestBody BooksDto booksDto) throws NotFoundException {
        log.info("ADD BOOK TO AUTHOR ID "+ id+" "+booksDto);
        bookService.addBook(id,booksDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
