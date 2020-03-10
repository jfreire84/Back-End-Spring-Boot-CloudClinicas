package com.backend.cloudclinicas.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.cloudclinicas.model.dao.InterfazClienteDao;
import com.backend.cloudclinicas.model.domain.Cliente;

@Service
public class impClienteService implements InterfazClienteService {

	//Esta clase implementa la interfaz que obliga a poner los métodos del Crud que esté en ella.
	
	//Inyectamos un objeto de la Interfaz de acceso a datos para usar el metodo FindAll del CrudRepository.
	@Autowired
	private InterfazClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

}
