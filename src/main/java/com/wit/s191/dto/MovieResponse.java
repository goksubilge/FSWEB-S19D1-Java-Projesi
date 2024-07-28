package com.wit.s191.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieResponse {
    private int id;
    private String name;
    private String directorName;
    private int rating;
    private LocalDate releaseDate;
    private List<ActorResponse> actors;

    public MovieResponse(int id, String name, String directorName, int rating, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.directorName = directorName;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public MovieResponse(int id, String name, String directorName, int rating, LocalDate releaseDate, List<ActorResponse> actors) {
        this.id = id;
        this.name = name;
        this.directorName = directorName;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.actors = actors;
    }
}
