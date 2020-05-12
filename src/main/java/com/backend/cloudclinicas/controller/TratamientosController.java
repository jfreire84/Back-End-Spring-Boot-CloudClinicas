package com.backend.cloudclinicas.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.backend.cloudclinicas.model.domain.Tratamientos;
import com.backend.cloudclinicas.model.service.ITratamientosService;



@CrossOrigin(origins = {"Http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class TratamientosController {
	
	
	@Autowired
	private ITratamientosService tratamientoService;
	
	//Método para buscar todos los tratamientos
	@GetMapping("/tratamientos")
	public List<Tratamientos> buscarTodos() {
		return tratamientoService.findAll();
	}
	
	//Método de la Url para buscar por Id.
		@GetMapping("/tratamientos/{id}")
		public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

			Map<String, Object> respuesta = new HashMap<>();

			// Aqui manejamos todos los errores que se generen en el servidor y la base de
			// datos.
			Tratamientos tratamiento = null;
			try {
				tratamiento = tratamientoService.findById(id);
			} catch (DataAccessException e) {
				respuesta.put("mensaje", "El tratamiento Id: ".concat(id.toString().concat("No existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

			}

			// Error si la factura es nulo.
			if (tratamiento == null) {
				respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Tratamientos>(tratamiento, HttpStatus.OK);
		}

		
		//Método de la URL para borrar tratamiento por id
		@DeleteMapping("/tratamientos/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id) {
			tratamientoService.delete(id);
		}
		
		
		//Método de la Url para crear el tratamiento
		
		@PostMapping("/tratamientos")
		@ResponseStatus(HttpStatus.CREATED)
		public Tratamientos crear(@RequestBody Tratamientos tratamiento) {
			return tratamientoService.create(tratamiento);
		}
		
		
		
		
		//URL para buscar tratamientos por nombre 
		
		@CrossOrigin(origins = {"Http://localhost:4200"})
		@GetMapping("/tratamientos/filtrar/{texto}")
		@ResponseStatus(HttpStatus.OK)
		public List<Tratamientos> buscarPorNombre(@PathVariable String texto){
			 return tratamientoService.findTratamientosByNombre(texto);
		}

}
