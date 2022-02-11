package com.retodolab.springboot.backend.reto.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retodolab.springboot.backend.reto.models.entity.Categoria;

public interface ICategoriaDao extends JpaRepository<Categoria,Long>{

}
