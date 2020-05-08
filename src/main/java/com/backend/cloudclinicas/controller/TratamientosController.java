package com.backend.cloudclinicas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

@CrossOrigin(origins = {"Http://localhost:4200"})
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
		public ResponseEntity<?> borrarTratamiento(@PathVariable Long id) {
		
			Map<String, Object> respuesta = new HashMap<>();

			try {
				tratamientoService.delete(id);
			} catch (DataAccessException e) {
				respuesta.put("mensaje", "Error al borrar el tratamiento");
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}

			respuesta.put("mensaje", " El tratamiento con ID: ".concat(id.toString()) + " ha sido borrada con éxito");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

		}
		
		
		//Método de la Url para crear el tratamiento
		@PostMapping("/tratamientos")
		public ResponseEntity<?> crearTratamiento(@Valid @RequestBody Tratamientos tratamiento, BindingResult br) {
			Tratamientos nuevoTratamiento= null;
			Map<String, Object> respuesta = new HashMap<>();

			if (br.hasErrors()) {

				// Creamos una lista para listar los errores.
				List<String> errores = new ArrayList<>();

				br.getFieldErrors();

				// Con un bucle for agregamos los arrores a nuestra lista creada.
				for (FieldError errors : br.getFieldErrors()) {
					errores.add("El campo: " + errors.getField() + " " + errors.getDefaultMessage());
				}

				// Leemos con un bucle for la lista de errores.
				respuesta.put("Errors", errores);
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
			}

			// Control de errores al insertar un nuevo cliente
			try {
				nuevoTratamiento = tratamientoService.create(tratamiento);
			} catch (DataAccessException e) {
				respuesta.put("mensaje", "Error al insertar el tratamiento en la base de datos");
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			respuesta.put("mensaje", "Tratamiento creado con exito");
			respuesta.put("mensaje", tratamientoService);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		}
	
		
		//URL para buscar productos por nombre
		
		@GetMapping("/tratamientos/filtrar/{texto}")
		@ResponseStatus(HttpStatus.OK)
		public List<Tratamientos> buscarPorNombre(@PathVariable String texto){
			 return tratamientoService.findTratamientosByNombre(texto);
		}

}
