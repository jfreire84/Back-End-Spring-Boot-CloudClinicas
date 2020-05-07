package com.backend.cloudclinicas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.cloudclinicas.model.dao.IFacturasDao;
import com.backend.cloudclinicas.model.domain.Factura;

@Service
public class FacturasServiceImpl implements IFacturasService {

	@Autowired
	IFacturasDao facturasDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		
		return (List<Factura>) facturasDao.findAll();
	}

	@Override
	public Factura findById(Long id) {
		// TODO Auto-generated method stub
		return facturasDao.findById(id).orElse(null);
	}

	@Override
	public Factura create(Factura factura) {
		return facturasDao.save(factura);
	}

	@Override
	public void delete(Long id) {
		facturasDao.deleteById(id);
		
	}

}
