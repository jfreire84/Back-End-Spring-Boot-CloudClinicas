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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cloudclinicas.model.domain.Cliente;
import com.backend.cloudclinicas.model.service.InterfazClienteService;

@CrossOrigin(origins = {"Http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private InterfazClienteService clienteService;

	// Metodo de la url para buscar todos los clientes.
	@GetMapping("/clientes")
	public List<Cliente> buscarTodos() {
		return clienteService.findAll();
	}

	
	// MÉTODO DE LA URL PARA BUSCAR POR ID.

	// En el controlador tenemos que controlar el manejo de errors, con la clase
	// ResponseEntity de Spring.
	// Controlamos el error al buscar un id de cliente que no existe o es nulo.
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		Map<String, Object> respuesta = new HashMap<>();

		// Aqui manejamos todos los errores que se generen en el servidor y la base de
		// datos.
		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "El cliente Id: ".concat(id.toString().concat("No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		// Error si el cliente es nulo.
		if (cliente == null) {
			respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// MÉTODO DE LA URL PARA CREAR UN CLIENTE

	@PostMapping("/clientes")
 	public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult br) {
		Cliente nuevoCliente = null;
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
			nuevoCliente = clienteService.save(cliente);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al insertar cliente en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Cliente creado con exito");
		respuesta.put("mensaje", nuevoCliente);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	// MÉTODO DE LA URL PARA ACTUALIZAR UN CLIENTE.

	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> actualizar(@Valid @RequestBody Cliente cliente, BindingResult br, @PathVariable Long id) {
		// Guardamos en un objeto de tipo cliente el cliente con el id buscado

		Cliente clienteBuscado;
		
		Map<String, Object> respuesta = new HashMap<>();

		if (br.hasErrors()) {

			// Creamos una lista para listar los errores.
			List<String> errores = new ArrayList<>();

			br.getFieldErrors();

			// Con un bucle for agregamos los arrores a nuestra lista creada.
			for (FieldError errors : br.getFieldErrors()) {
				errores.add("El campo que deseas Actualizar: " + errors.getField() + " " + errors.getDefaultMessage());
			}

			// Leemos con un bucle for la lista de errores.
			respuesta.put("Errors", errores);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		//Controlamos los errores con TryCatch
		try {
			clienteBuscado = clienteService.findById(id);
		}catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al actualizar el cliente");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		// Error si el id del cliente es nulo.
		if (clienteBuscado == null) {
			respuesta.put("mensaje", "Error al realizar la actualizacion en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		
		clienteBuscado.setApellidos(cliente.getApellidos());
		clienteBuscado.setNombre(cliente.getNombre());
		clienteBuscado.setEmail(cliente.getEmail());
		clienteBuscado.setHistorial(cliente.getHistorial());
		clienteBuscado.setFechanac(cliente.getFechanac());
		clienteService.save(clienteBuscado);
		
		
		respuesta.put("Mensaje", "Cliente actualizado con éxito");
		respuesta.put("mensaje", clienteBuscado);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		
	}

	// MÉTODO DE LA URL PARA BORRAR EL CLIENTE.
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
		// clienteService.delete(id);

		Map<String, Object> respuesta = new HashMap<>();

		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Error al borrar el cliente");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		respuesta.put("mensaje", " El Cliente con ID: ".concat(id.toString()) + " ha sido borrado con éxito");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}

}
