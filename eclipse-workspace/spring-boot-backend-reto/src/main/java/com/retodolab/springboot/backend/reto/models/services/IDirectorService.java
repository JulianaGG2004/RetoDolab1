package com.retodolab.springboot.backend.reto.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.retodolab.springboot.backend.reto.models.entity.Director;



public interface IDirectorService {
	public List<Director> findAll();
	
	public Page<Director> findAll(Pageable pageable);
	
	public Director findById(Long id);
	
	public Director save(Director director);
	
	public void delete(Long id);
}
