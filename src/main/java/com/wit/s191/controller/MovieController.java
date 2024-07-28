package com.wit.s191.controller;

import com.wit.s191.dto.ActorResponse;
import com.wit.s191.dto.MovieActorRequest;
import com.wit.s191.dto.MovieResponse;
import com.wit.s191.entity.Actor;
import com.wit.s191.entity.Movie;
import com.wit.s191.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Bir adet movie objesi ve bir adet actor objesi alır ve ikisini de veritabanına ilişkileri ile birlikte kaydeder.
    @PostMapping("/")
    public MovieResponse save(@RequestBody MovieActorRequest movieActorRequest){
        Movie movie = movieActorRequest.getMovie();
        List<Actor> actorList = movieActorRequest.getActorList();

        for(Actor actor : actorList){
            movie.addActor(actor);
        }

        Movie savedMovie = movieService.save(movie);
        return new MovieResponse(savedMovie.getId(), savedMovie.getName(), savedMovie.getDirectorName(), savedMovie.getRating(), savedMovie.getReleaseDate());
    }

    // Bir adet movie objesini actor leri ile beraber getirir.
    @GetMapping("/{movieId}")
    public MovieResponse find(@PathVariable int movieId){
        Movie movie = movieService.findByIdMovie(movieId);
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor: movie.getActorList()){
            actorResponses.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getGender().name(),actor.getBirthDate()));
        }
        return new MovieResponse(movie.getId(),movie.getName(),movie.getDirectorName(),movie.getRating(),movie.getReleaseDate(), actorResponses);
    }
}
