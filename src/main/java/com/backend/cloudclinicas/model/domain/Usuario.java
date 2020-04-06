package com.backend.cloudclinicas.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	//Columnas de los campos de usuarios en la base de datos mapeado por JPA.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20)
	private String nombre;
	
	@Column(length=60)
	private String contrasena;
	
	private Boolean habilitado;
	
	//Indicamos la relacion de Jpa. Muchos a muchos. Esto va a crear una tabla intermedia usuario_roles.
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Roles> roles;

	public Long getId() {
		return id;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre_usuario) {
		this.nombre = nombre_usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
