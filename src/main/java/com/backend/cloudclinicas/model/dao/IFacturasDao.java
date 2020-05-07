package com.backend.cloudclinicas.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.cloudclinicas.model.domain.Cliente;
import com.backend.cloudclinicas.model.domain.Factura;

@Repository
public interface IFacturasDao extends CrudRepository<Factura, Long>   {

}
