package com.retodolab.springboot.backend.reto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retodolab.springboot.backend.reto.models.dao.IPeliculaDao;
import com.retodolab.springboot.backend.reto.models.entity.Actor;
import com.retodolab.springboot.backend.reto.models.entity.Categoria;
import com.retodolab.springboot.backend.reto.models.entity.Director;
import com.retodolab.springboot.backend.reto.models.entity.Pelicula;



@Service
public class PeliculaServiceImpl implements IPeliculaService{
	
	@Autowired
	private IPeliculaDao peliculaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {
		
		return (List<Pelicula>) peliculaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Pelicula> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return peliculaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findById(Long id) {
		// TODO Auto-generated method stub
		return peliculaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pelicula save(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return peliculaDao.save(pelicula);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		peliculaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllCategorias() {
		// TODO Auto-generated method stub
		return peliculaDao.findAllCategorias();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Actor> findAllActores() {
		// TODO Auto-generated method stub
		return peliculaDao.findAllActores();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Director> findAllDirectores() {
		// TODO Auto-generated method stub
		return peliculaDao.findAllDirectores();
	}

}
