package com.backend.cloudclinicas.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	/*
	 * Clase POJO mapeada por hibernate para crear en la base de datos la tabla y
	 * las columnas con estos atributos.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty(message = "Nombre no puede estar vacío")
	@Size(min = 4, max = 12)
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "Apellidos no puede estar vacío")
	private String apellidos;

	// Mediante el uso de @Temporal es posible determinar que nuestro atributo
	// almacena Hora.
	@Temporal(TemporalType.DATE)
	private Date fechanac;

	@NotEmpty(message = "Email no puede estar vacío")
	@Email(message = "No es una dirección de correo correcta")
	@Column(nullable = false, unique = true)
	private String email;

	@Length(min = 10, max = 1000)
	private String historial;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	// Anotación para crear la fecha de forma automática en el atributo Fecha.
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	// Atributo List facturas para crear la relacion con el cliente. De tipo un
	// cliente muchas facturas.
	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> facturas;

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Cliente() {
		this.facturas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
