package com.backend.cloudclinicas.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.cloudclinicas.model.domain.Cliente;

@Repository
public interface InterfazClienteDao extends JpaRepository<Cliente, Long>  {
	
	/*Creamos la clase de acceso a datos utilizando Spring Data JPA.
	Se hereda de la Interfaz JpaRepository 
	que ya viene con los metodos basicos para hacer un CRUD y no tenemos que escribir código SQL
	y además tiene la clase para hacer paginación*/

}
