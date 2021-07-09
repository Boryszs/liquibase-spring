package com.example.liquibase.service.impl;

import com.example.liquibase.dto.BooksResponseDto;
import com.example.liquibase.repository.BookRepository;
import com.example.liquibase.service.BookService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BooksResponseDto> findAll() {
        return bookRepository.findAll().stream().map(books -> modelMapper.map(books, BooksResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BooksResponseDto> findAllPagination(Long pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo.intValue(), pageSize);
        return bookRepository.findAll(paging).getContent().stream().map(books -> modelMapper.map(books, BooksResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public BooksResponseDto findById(Long id) throws NotFoundException {
        if (bookRepository.findById(id).isPresent()) {
            return modelMapper.map(bookRepository.findById(id).get(), BooksResponseDto.class);
        } else {
            throw new NotFoundException("Not found person on id:" + id);
        }
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found person on id:" + id);
        }
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
