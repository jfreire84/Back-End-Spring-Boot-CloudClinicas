package com.backend.cloudclinicas.model.service;

import java.util.List;
import com.backend.cloudclinicas.model.domain.Tratamientos;

public interface ITratamientosService {
	
	//Método para listar todos los Tratamientos
	
	public List<Tratamientos> findAll();
	
	//Método para buscar el Tratamientos por id.
	
	public Tratamientos findById(Long id);
	
	//Método para crear Tratamientos.
	
	public Tratamientos create(Tratamientos tratamiento);
	
	//Método para borrar Tratamientos
	
	public void delete(Long id);
	
	//Método para buscar por nombre
	public List<Tratamientos> findTratamientosByNombre(String nombre);
	

}
