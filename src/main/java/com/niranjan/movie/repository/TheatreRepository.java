package com.niranjan.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niranjan.movie.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
