package com.retodolab.springboot.backend.reto.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name="peliculas")
public class Pelicula  implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="No puede estar vacio")
	@Size(min=4, max=12, message="El tamaño tiene que estar entre 4 y 12")
	@Column(nullable=false)
	private String nombre;
	
	@NotEmpty(message="No puede estar vacio")
	@Column(nullable=false)
	private String descripcion;
	
	@NotNull(message="No puede estar vacio")
	@Column(name="fecha_publicacion")
	@Temporal(TemporalType.DATE)
	private Date fecha_publicacion;
	
	private String foto;
	
	@NotNull(message="La categoria no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	
	@NotNull(message="El campo no puede ser vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="actor_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Actor actor;
	
	@NotNull(message="El campo no puede ser vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="director_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Director director;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}





	private static final long serialVersionUID = 1L;

}
