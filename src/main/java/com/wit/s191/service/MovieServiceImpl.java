package com.wit.s191.service;

import com.wit.s191.entity.Movie;
import com.wit.s191.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findByIdMovie(int id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()){
            return movieOptional.get();
        }
        throw new RuntimeException("Id is not exist: " + id);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(int id) {
        Movie movie = findByIdMovie(id);
        movieRepository.delete(movie);
        return movie;
    }
}
