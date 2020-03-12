package com.backend.cloudclinicas.model.service;

import java.util.List;

import com.backend.cloudclinicas.model.domain.Cliente;

public interface InterfazClienteService {
	
	//Creamos una Interfaz con un métodos que van a implementar la clase impClienteService.
	
	//Metodo para listar todos.
	public List<Cliente> findAll();
	
	//Método para buscar por id
	public Cliente findById(Long id);
	
	//Método para crear un cliente
	public Cliente save(Cliente cliente);
	
	//Metodo para borrar un cliente por id
	public void delete(Long id);

}
