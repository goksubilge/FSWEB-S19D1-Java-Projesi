package com.wit.s191.service;

import com.wit.s191.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
    Actor findByIdActor(int id);
    Actor save(Actor actor);
    Actor delete(int id);
}
