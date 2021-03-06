package com.retodolab.springboot.backend.reto.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retodolab.springboot.backend.reto.models.entity.Actor;
import com.retodolab.springboot.backend.reto.models.entity.Categoria;
import com.retodolab.springboot.backend.reto.models.entity.Director;
import com.retodolab.springboot.backend.reto.models.entity.Pelicula;
import com.retodolab.springboot.backend.reto.models.services.IPeliculaService;
import com.retodolab.springboot.backend.reto.models.services.IUploadFileService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PeliculaRestController {
	
	@Autowired 
	private IPeliculaService peliculaService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@GetMapping("/peliculas")
	public List<Pelicula> index(){
		return peliculaService.findAll();
	}
	
	@GetMapping("/peliculas/page/{page}")
	public Page<Pelicula> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return peliculaService.findAll(pageable);
	}
	
	@GetMapping("/peliculas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
	Pelicula pelicula= null;
	Map<String, Object> response = new HashMap<>();
	try {
		pelicula = peliculaService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		
		if(pelicula == null) {
			response.put("mensaje", "La pelicula ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pelicula>(pelicula, HttpStatus.OK); 
	}
	
	@PostMapping("/peliculas")
	
	public ResponseEntity<?> create(@Valid @RequestBody Pelicula pelicula, BindingResult result) {
		
		Pelicula peliculaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->"El campo '"+ err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		try {
			peliculaNew = peliculaService.save(pelicula);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La pelicula ha sido creada con ??xito!");
		response.put("pelicula", peliculaNew);
		return new ResponseEntity<Map<String,Object>> (response,HttpStatus.CREATED);
	}
	
	@PutMapping("/peliculas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Pelicula pelicula, BindingResult result,@PathVariable Long id) {
		Pelicula peliculaActual = peliculaService.findById(id);		
		
		Pelicula peliculaUpdated= null;
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->"El campo '"+ err.getField()+"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		if(peliculaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, La pelicula ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			peliculaActual.setNombre(pelicula.getNombre());
			peliculaActual.setDescripcion(pelicula.getDescripcion());
			peliculaActual.setFecha_publicacion(pelicula.getFecha_publicacion());
			peliculaActual.setCategoria(pelicula.getCategoria());
			peliculaActual.setActor(pelicula.getActor());
			peliculaActual.setDirector(pelicula.getDirector());
			peliculaActual = peliculaService.save(peliculaActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La pelicula ha sido actulizado con ??xito!");
		response.put("pelicula", peliculaUpdated);
		return new ResponseEntity<Map<String,Object>> (response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/peliculas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pelicula pelicula = peliculaService.findById(id);
			String nombreFotoAnterior = pelicula.getFoto();
			uploadService.eliminar(nombreFotoAnterior);

			peliculaService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al Eliminar la pelicula en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La pelicula ha sido eliminado con ??xito!");
		
		return new ResponseEntity<Map<String,Object>> (response,HttpStatus.OK);
	}
	
	@PostMapping("/peliculas/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Pelicula pelicula = peliculaService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo=uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen " );
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = pelicula.getFoto();
			uploadService.eliminar(nombreFotoAnterior);

			pelicula.setFoto(nombreArchivo);
			
			peliculaService.save(pelicula);
			
			response.put("pelicula", pelicula);
			response.put("mensaje","Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		
		return new ResponseEntity<Map<String,Object>> (response,HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso= null;
		
		try {
			recurso =uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + recurso.getFilename() +"\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/peliculas/categorias")
	public List<Categoria> listarCategorias(){
		return peliculaService.findAllCategorias();
	}
	
	@GetMapping("/peliculas/actores")
	public List<Actor> listarActores(){
		return peliculaService.findAllActores();
	}
	
	@GetMapping("/peliculas/directores")
	public List<Director> listarDirectores(){
		return peliculaService.findAllDirectores();
	}
	
}
