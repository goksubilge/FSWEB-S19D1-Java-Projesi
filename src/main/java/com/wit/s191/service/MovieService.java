package com.wit.s191.service;

import com.wit.s191.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findByIdMovie(int id);
    Movie save(Movie movie);
    Movie delete(int id);
}
