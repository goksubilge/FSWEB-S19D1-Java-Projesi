package com.wit.s191.service;

import com.wit.s191.entity.Actor;
import com.wit.s191.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findByIdActor(int id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if(actorOptional.isPresent()){
            return actorOptional.get();
        }
        throw new RuntimeException("Id is not exist: " + id);
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor delete(int id) {
        Actor actor = findByIdActor(id);
        actorRepository.delete(actor);
        return actor;
    }
}
