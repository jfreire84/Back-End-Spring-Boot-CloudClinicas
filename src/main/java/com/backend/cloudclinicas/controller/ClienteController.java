package com.backend.cloudclinicas.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private InterfazClienteService clienteService;
	
	//Metodo de la url para buscar todos los clientes.
	@GetMapping("/clientes")
	public List<Cliente> buscarTodos(){
		return clienteService.findAll();
	}
	
	//MÉTODO DE LA URL PARA BUSCAR POR ID.

	//En el controlador tenemos que controlar el manejo de errors, con la clase ResponseEntity de Spring.
	//Controlamos el error al buscar un id de cliente que no existe o es nulo.
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
				
		Map<String, Object> respuesta = new HashMap<>();
		
		//Aqui manejamos todos los errores que se generen en el servidor y la base de datos.
		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "El cliente Id: ".concat(id.toString().concat("No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		//Error si el cliente es nulo.
		if(cliente == null) {
			respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK); 
	}
	
	//MÉTODO DE LA URL PARA CREAR UN CLIENTE
	
	@PostMapping("/clientes")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = null;
		Map<String, Object> respuesta = new HashMap<>();
		
		
		//Control de errores al insertar un nuevo cliente
		try {
			nuevoCliente = clienteService.save(cliente);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "Error al insertar cliente en la base de datos");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "Cliente creado con exito");
		respuesta.put("mensaje", nuevoCliente);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED );
	}
	
	
	//MÉTODO DE LA URL PARA ACTUALIZAR UN CLIENTE.
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente actualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
		//Guardamos en un objeto de tipo cliente el cliente con el id buscado
		Cliente clienteBuscado = clienteService.findById(id);
		clienteBuscado.setApellidos(cliente.getApellidos());
		clienteBuscado.setNombre(cliente.getNombre());
		clienteBuscado.setEmail(cliente.getEmail());
		clienteBuscado.setHistorial(cliente.getHistorial());
		
		return clienteService.save(clienteBuscado);
	}
	
	//Método para borrar usuario.
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrarCliente(@PathVariable Long id) {
		clienteService.delete(id);
	}

}

	
	
	
	
	
	
	
	
	
	