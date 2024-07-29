package com.wit.s191.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name= "movie", schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "director_name")
    private String directorName;
    @Column(name = "rating")
    private int rating;
    @Column(name = "release_date")
    @Past
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="actor_movie", schema = "fsweb",
    joinColumns = @JoinColumn (name="movie_id"),
    inverseJoinColumns = @JoinColumn( name= "actor_id"))
    private List<Actor> actorList;

    // utility function
    public void addActor(Actor actor){
        if(actorList == null){
            actorList = new ArrayList<>();
        }
        actorList.add(actor);
    }
}
