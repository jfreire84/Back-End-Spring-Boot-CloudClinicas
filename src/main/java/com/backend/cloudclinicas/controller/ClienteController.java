package com.backend.cloudclinicas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cloudclinicas.model.domain.Cliente;
import com.backend.cloudclinicas.model.service.InterfazClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private InterfazClienteService buscarCliente;
	
	@GetMapping("/buscar")
	public List<Cliente> buscarCliente(){
		return buscarCliente.findAll();
	}

}

	
	
	
	
	
	
	
	
	
	