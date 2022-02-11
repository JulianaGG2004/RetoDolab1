package com.retodolab.springboot.backend.reto.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.retodolab.springboot.backend.reto.models.entity.Actor;

public interface IActorService {
	
	public List<Actor> findAll();
	
	public Page<Actor> findAll(Pageable pageable);
	
	public Actor findById(Long id);
	
	public Actor save(Actor actor);
	
	public void delete(Long id);
	
}
