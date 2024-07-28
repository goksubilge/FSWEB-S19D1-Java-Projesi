package com.wit.s191.dto;

import com.wit.s191.entity.Actor;
import com.wit.s191.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private List<Actor> actorList;
}
