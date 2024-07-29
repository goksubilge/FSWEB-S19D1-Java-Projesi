package com.wit.s191.controller;

import com.wit.s191.dto.ActorResponse;
import com.wit.s191.dto.MovieActorRequest;
import com.wit.s191.dto.MovieResponse;
import com.wit.s191.entity.Actor;
import com.wit.s191.entity.Movie;
import com.wit.s191.service.ActorService;
import com.wit.s191.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*") //frondEnd 'inin domain adresi olacak burası => localhost:8080
@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;
    private ActorService actorService;

    @Autowired
    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    // movie + movie's actorList save (to SQL with relations)
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

    // movie(given id) + movie's actorList get
    @GetMapping("/{movieId}")
    public MovieResponse find(@PathVariable int movieId){
        Movie movie = movieService.findByIdMovie(movieId);
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor: movie.getActorList()){
            actorResponses.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getGender().name(),actor.getBirthDate()));
        }
        return new MovieResponse(movie.getId(),movie.getName(),movie.getDirectorName(),movie.getRating(),movie.getReleaseDate(), actorResponses);
    }

    // movie(given id) add movie's actorList
    @PutMapping("/addActor/{movieId}")
    public MovieResponse addActor(@PathVariable int movieId, @RequestBody Actor actor){
        Movie movie = movieService.findByIdMovie(movieId);
        movie.addActor(actor);
        // actor.addMovie(movie); => OneToMany için uygun ve mantıklı, ama ManyToMany relation larda junk table 'a çift atama yapıyor bozuk oluyor. O yüzden ManyToMany 'de çift kayıt yapmıyoruz.

/*      // 1) Actor 'ü movie 'ye ekledim.    2) actor 'ü save ledim.   Tek satırda böyle de yapabilirdim. Ama yukarıdaki kod daha iyi çünkü MovieControllerdayken movie'sinden götürmek çok daha iyi bir alışkanlıktır.
        actor.addMovie(movieService.findByIdMovie(movieId));
        actorService.save(actor);
*/
        Movie savedMovie = movieService.save(movie);

        return new MovieResponse(savedMovie.getId(), savedMovie.getName(), savedMovie.getDirectorName(), savedMovie.getRating(), savedMovie.getReleaseDate());


    }
}
