package com.retodolab.springboot.backend.reto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retodolab.springboot.backend.reto.models.dao.IDirectorDao;
import com.retodolab.springboot.backend.reto.models.entity.Director;

@Service
public class DirectorServiceImpl implements IDirectorService{
	@Autowired
	private IDirectorDao directorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Director> findAll() {
		return (List<Director>) directorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Director> findAll(Pageable pageable) {
		return directorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Director findById(Long id) {
		return directorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Director save(Director director) {
		return directorDao.save(director);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		directorDao.deleteById(id);
	}
}
