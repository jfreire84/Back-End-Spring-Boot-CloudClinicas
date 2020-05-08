package com.backend.cloudclinicas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.cloudclinicas.model.dao.ITratamientosDao;
import com.backend.cloudclinicas.model.domain.Tratamientos;

@Service
public class TratamientoServiceImpl implements ITratamientosService {

	@Autowired
	ITratamientosDao tratamientoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tratamientos> findAll() {
		
		return (List<Tratamientos>) tratamientoDao.findAll();
	}

	@Override
	public Tratamientos findById(Long id) {
		// TODO Auto-generated method stub
		return tratamientoDao.findById(id).orElse(null);
	}

	@Override
	public Tratamientos create(Tratamientos factura) {
		return tratamientoDao.save(factura);
	}

	@Override
	public void delete(Long id) {
		tratamientoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratamientos> findTratamientosByNombre(String nombre) {
		return tratamientoDao.findByNombre(nombre);
	}

}
