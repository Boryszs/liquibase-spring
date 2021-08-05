package com.example.liquibase.service.impl;

import com.example.liquibase.dto.BooksDto;
import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.mapper.Mapper;
import com.example.liquibase.model.Authors;
import com.example.liquibase.model.Books;
import com.example.liquibase.repository.AuthorRepository;
import com.example.liquibase.repository.BookRepository;
import com.example.liquibase.service.AuthorService;
import com.example.liquibase.service.BookService;
import javassist.NotFoundException;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Mapper<BooksResponseDto, Books> booksMapperImpl;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Mapper<BooksResponseDto, Books> booksMapperImpl, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.booksMapperImpl = booksMapperImpl;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BooksResponseDto> findAll() {
        return bookRepository.findAll().stream().map(books -> booksMapperImpl.toDto(books)).collect(Collectors.toList());
    }

    @Override
    public List<BooksResponseDto> findAllPagination(Long pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo.intValue(), pageSize);
        return bookRepository.findAll(paging).stream().map(books -> booksMapperImpl.toDto(books)).collect(Collectors.toList());
    }

    @Override
    public BooksResponseDto findById(Long id) throws NotFoundException {
        return booksMapperImpl.toDto(
                bookRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Not found person on id:" + id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found person on id:" + id);
        }
    }

    @Override
    @Transactional
    public void addBook(Long id, BooksDto booksDto) throws NotFoundException {
        Optional<Authors> authors = authorRepository.findById(id);
        if (authors.isPresent()) {
            List<Authors> authorsList = new ArrayList<>();
            authorsList.add(authors.get());
            List<Books> books = authors.get().getBooks();

            Books book = bookRepository.save(Books
                    .builder()
                    .title(booksDto.getTitle())
                    .available(booksDto.getAvailable())
                    .image(booksDto.getImage())
                    .description(booksDto.getDescription())
                    .published(booksDto.getPublished())
                    .authors(authorsList)
                    .build());
            books.add(book);
            authors.get().setBooks(books);
            authorRepository.save(authors.get());

        } else {
            throw new NotFoundException("Not found person on id:" + id);
        }
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
