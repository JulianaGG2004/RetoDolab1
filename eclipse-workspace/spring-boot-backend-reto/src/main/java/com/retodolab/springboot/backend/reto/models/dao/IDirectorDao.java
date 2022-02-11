package com.retodolab.springboot.backend.reto.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.retodolab.springboot.backend.reto.models.entity.Director;

public interface IDirectorDao extends JpaRepository<Director,Long>{

}
