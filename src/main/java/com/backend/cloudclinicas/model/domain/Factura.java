package com.backend.cloudclinicas.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;
	private String observacion;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacionFac;

	// Atributo cliente para hacer la relacion con el cliente. De tipo Muchas facturas relacionado con solo un cliente.
	@JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
 
	
	//Atributo para la relacion de tablas con JoinColum.
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<LineasFactura> lineasFactura;
	
	//Generamos constructor para crear la factura con las lineas
	public Factura() {
		lineasFactura = new ArrayList<>();
	}
	

	
	//Anotación para crear la fecha de forma automática en el atributo Fecha.
		@PrePersist
		public void prePersist() {
			fechaCreacionFac = new Date();
		}


	//Getters y Setters	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaCreacionFac() {
		return fechaCreacionFac;
	}

	public void setFechaCreacionFac(Date fechaCreacionFac) {
		this.fechaCreacionFac = fechaCreacionFac;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public List<LineasFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(List<LineasFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
	
	//Metodo para calcular el total de todas las lineas de la factura.
	public Double totalImporteFactura() {
		Double total = 0.00;
		for(LineasFactura linea: lineasFactura) {
			total += linea.getImporte();
		}
		return total;
	}




	private static final long serialVersionUID = 1L;

}
