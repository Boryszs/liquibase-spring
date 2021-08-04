package com.example.liquibase.service.impl;

import com.example.liquibase.dto.AuthorsDto;
import com.example.liquibase.dto.AuthorsResponseDto;
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

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorsResponseDto> findAll() {
        return authorRepository.findAll().stream().map(authors -> modelMapper.map(authors, AuthorsResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorsResponseDto findById(Long id) throws NotFoundException {
        if(authorRepository.findById(id).isPresent()){
           return modelMapper.map(authorRepository.findById(id).get(), AuthorsResponseDto.class);
        }else{
            throw new NotFoundException("Not found person on id:"+id);
        }
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(authorRepository.findById(id).isPresent()){
            authorRepository.deleteById(id);
        }else{
            throw new NotFoundException("Not found person on id:"+id);
        }
    }

    @Override
    public void save(AuthorsDto authorsDto) {
        authorRepository.save(modelMapper.map(authorsDto, Authors.class));
    }

    @Override
    public void save(AuthorsResponseDto authorsResponseDto) {
        authorRepository.save(modelMapper.map(authorsResponseDto,Authors.class));
    }
}
