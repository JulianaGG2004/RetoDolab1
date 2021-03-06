package com.retodolab.springboot.backend.reto.models.services;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retodolab.springboot.backend.reto.models.dao.IActorDao;
import com.retodolab.springboot.backend.reto.models.entity.Actor;

@Service
public class ActorServiceImpl implements IActorService{
	
	@Autowired
	private IActorDao actorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Actor> findAll() {
		return (List<Actor>) actorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Actor> findAll(Pageable pageable) {
		return actorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Actor findById(Long id) {
		return actorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Actor save(Actor actor) {
		return actorDao.save(actor);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		actorDao.deleteById(id);
	}
	
}
