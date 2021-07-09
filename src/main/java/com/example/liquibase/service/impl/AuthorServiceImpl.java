package com.example.liquibase.service.impl;

import com.example.liquibase.dto.AuthorsResponseDto;
import com.example.liquibase.repository.AuthorRepository;
import com.example.liquibase.service.AuthorService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper = new ModelMapper();

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
}
