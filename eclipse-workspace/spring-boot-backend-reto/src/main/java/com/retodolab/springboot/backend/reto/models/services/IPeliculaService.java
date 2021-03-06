package com.retodolab.springboot.backend.reto.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.retodolab.springboot.backend.reto.models.entity.Actor;
import com.retodolab.springboot.backend.reto.models.entity.Categoria;
import com.retodolab.springboot.backend.reto.models.entity.Director;
import com.retodolab.springboot.backend.reto.models.entity.Pelicula;



public interface IPeliculaService {
	
public List<Pelicula> findAll();
	
	public Page<Pelicula> findAll(Pageable pageable);
	
	public Pelicula findById(Long id);
	
	public Pelicula save(Pelicula pelicula);
	
	public void delete(Long id);
	
	public List<Categoria> findAllCategorias();
	
	public List<Actor> findAllActores();
	
	public List<Director> findAllDirectores();
}
