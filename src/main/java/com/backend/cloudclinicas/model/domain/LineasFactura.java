package com.backend.cloudclinicas.model.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lineas_factura")
public class LineasFactura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;

	// Atributo Tratamientos para hacer la relacion de las tablas
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tratamiento_id")
	private Tratamientos tratamiento;

	// Método para calcular el importe total de la linea del item.
	public Double getImporte() {
		return cantidad.doubleValue() * tratamiento.getPrecio();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Tratamientos getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamientos tratamiento) {
		this.tratamiento = tratamiento;
	}

	private static final long serialVersionUID = 1L;

}
