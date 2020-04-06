package com.backend.cloudclinicas.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.cloudclinicas.model.domain.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {
	
	//Creamos el método para buscar por nombre de usuario.
	public Usuario findBynombre(String usuario);

}
