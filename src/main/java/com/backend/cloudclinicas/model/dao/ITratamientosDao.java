package com.backend.cloudclinicas.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.backend.cloudclinicas.model.domain.Tratamientos;

@Repository
public interface ITratamientosDao extends CrudRepository<Tratamientos, Long>   {
	
	
	//Query para hacer la busqueda del tratamiento por nombre.
	@Query("select p from Tratamientos p where p.nombre like %?1%")
	public List<Tratamientos> findByNombre(String nombre);
	
	
	//Usando Jpa
	public List<Tratamientos> findByNombreContainingIgnoreCase(String nombre);
	
	public List<Tratamientos> findByNombreStartingWithIgnoreCase(String nombre);
	

}
