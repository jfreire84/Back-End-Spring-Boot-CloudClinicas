package com.backend.cloudclinicas.model.service;

import java.util.List;

import com.backend.cloudclinicas.model.domain.Cliente;

public interface InterfazClienteService {
	
	//Creamos una Interfaz con un método tipo lista listar a los clientes
	//para que tengan que implementar en las clases que implementan estas interfaz.
	
	public List<Cliente> findAll();

}
