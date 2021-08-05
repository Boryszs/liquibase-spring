package com.example.liquibase.service.impl;

import com.example.liquibase.dto.AuthorsDto;
import com.example.liquibase.dto.AuthorsResponseDto;
import com.example.liquibase.mapper.Mapper;
import com.example.liquibase.model.Authors;
import com.example.liquibase.repository.AuthorRepository;
import com.example.liquibase.service.AuthorService;
import javassist.NotFoundException;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Mapper<AuthorsResponseDto, Authors> authorMapperImpl;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, Mapper<AuthorsResponseDto, Authors> authorMapperImpl) {
        this.authorRepository = authorRepository;
        this.authorMapperImpl = authorMapperImpl;
    }

    @Override
    public List<AuthorsResponseDto> findAll() {
        return authorRepository.findAll().stream().map(authors -> authorMapperImpl.toDto(authors)).collect(Collectors.toList());
    }

    @Override
    public AuthorsResponseDto findById(Long id) throws NotFoundException {
        return authorMapperImpl
                .toDto(authorRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Not found person on id:" + id)));
    }

    @Override
    public void deleteById(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            new NotFoundException("Not found person on id:" + id);
        }

    }

    @Override
    public void save(AuthorsDto authorsDto) {
        authorRepository.save(modelMapper.map(authorsDto, Authors.class));
    }

    @Override
    public void save(AuthorsResponseDto authorsResponseDto) {
        authorRepository.save(modelMapper.map(authorsResponseDto, Authors.class));
    }
}
