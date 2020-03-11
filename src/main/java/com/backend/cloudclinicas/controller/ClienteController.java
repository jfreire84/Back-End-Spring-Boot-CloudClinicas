package com.backend.cloudclinicas.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//Metodo de la url para buscar cliente por id.
	@GetMapping("/clientes/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	//Método de la url para crear el cliente
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}

}

	
	
	
	
	
	
	
	
	
	