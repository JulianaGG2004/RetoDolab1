package com.retodolab.springboot.backend.reto.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retodolab.springboot.backend.reto.models.entity.Actor;
import com.retodolab.springboot.backend.reto.models.entity.Categoria;
import com.retodolab.springboot.backend.reto.models.entity.Director;
import com.retodolab.springboot.backend.reto.models.entity.Pelicula;



public interface IPeliculaDao extends JpaRepository<Pelicula,Long>{
	@Query("from Categoria")
	public List<Categoria> findAllCategorias();
	
	@Query("from Actor")
	public List<Actor> findAllActores();
	
	@Query("from Director")
	public List<Director> findAllDirectores();
}
