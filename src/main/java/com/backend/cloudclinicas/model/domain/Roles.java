package com.backend.cloudclinicas.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roles implements Serializable {

	// Columnas de los campos de roles en la base de datos mapeado por JPA.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String nombre_role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_role() {
		return nombre_role;
	}

	public void setNombre_role(String nombre_role) {
		this.nombre_role = nombre_role;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
