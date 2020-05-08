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
import com.backend.cloudclinicas.model.domain.Factura;
import com.backend.cloudclinicas.model.service.IFacturasService;

@CrossOrigin(origins = {"Http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturasController {
	
	@Autowired
	IFacturasService facturaService;
	
	//Metodo de la URL para listar las Facturas
	
	@GetMapping("/facturas")
	public List<Factura> listarTodas(){
		return facturaService.findAll();
	}
	
	//Método de la Url para buscar por Id.
	@GetMapping("/facturas/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		Map<String, Object> respuesta = new HashMap<>();

		// Aqui manejamos todos los errores que se generen en el servidor y la base de
		// datos.
		Factura factura = null;
		try {
			factura = facturaService.findById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "La factura Id: ".concat(id.toString().concat("No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		// Error si la factura es nulo.
		if (factura == null) {
			respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Factura>(factura, HttpStatus.OK);
	}

	//Método de la Url para crear la factura
	@PostMapping("/facturas")
	public ResponseEntity<?> crearFactura(@Valid @RequestBody Factura factura, BindingResult br) {
		Factura nuevaFactura= null;
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
			nuevaFactura = facturaService.create(factura);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al insertar factura en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Factura creada con exito");
		respuesta.put("mensaje", facturaService);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	
	//Método de la URL para borrar factura por id
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> borrarFactura(@PathVariable Long id) {
	
		Map<String, Object> respuesta = new HashMap<>();

		try {
			facturaService.delete(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al borrar la factura");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		respuesta.put("mensaje", " La factura con ID: ".concat(id.toString()) + " ha sido borrada con éxito");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}
}
