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
@Table(name= "actor", schema = "fsweb")
public class Actor {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    //@Column(name = "gender")
    private Gender gender;
    @Column(name = "birth_date")
    @Past
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="actor_movie", schema = "fsweb", joinColumns = @JoinColumn(name="actor_id"),
    inverseJoinColumns = @JoinColumn(name="movie_id"))
    private List<Movie> movieList;

    // utility fuction
    public void addMovie(Movie movie){
        if(movieList == null){
            movieList = new ArrayList<>();
        }
        movieList.add(movie);
    }

}
