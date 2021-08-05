package com.example.liquibase.mapper;

public interface Mapper <T,Z>{
    T toDto(Z z);
}
