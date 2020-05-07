package com.backend.cloudclinicas.model.service;

import java.util.List;

import com.backend.cloudclinicas.model.domain.Factura;

public interface IFacturasService {
	
	//Método paralistar todas las facturas
	
	public List<Factura> findAll();
	
	//Método para buscar la factura por id.
	
	public Factura findById(Long id);
	
	//Método para crear Factura.
	
	public Factura create(Factura factura);
	
	//Método para borrar Factura
	
	public void delete(Long id);
	

}
