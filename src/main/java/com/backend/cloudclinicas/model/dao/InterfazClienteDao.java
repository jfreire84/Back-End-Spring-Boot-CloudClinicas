package com.backend.cloudclinicas.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.cloudclinicas.model.domain.Cliente;

@Repository
public interface InterfazClienteDao extends CrudRepository<Cliente, Long>  {
	
	/*Creamos la clase de acceso a datos utilizando Spring Data JPA.
	Se hereda de la Interfaz CrudRepository 
	que ya viene con los metodos basicos para hacer un CRUD y no tenemos que escribir código SQL.*/

}
