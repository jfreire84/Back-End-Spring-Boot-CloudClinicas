package com.backend.cloudclinicas.model.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.backend.cloudclinicas.model.domain.Cliente;

public interface InterfazClienteService {
	
	//Creamos una Interfaz con un método tipo lista listar a los clientes
	//para que tengan que implementar en las clases que implementan estas interfaz.
	
	@Transactional
	public List<Cliente> findAll();
	
	//Método para buscar por id
	public Cliente findById(Long id);
	
	//Método para crear un cliente
	public Cliente save(Cliente cliente);
	
	//Metodo para borrar un clientepor id
	public void delete(Long id);

}
