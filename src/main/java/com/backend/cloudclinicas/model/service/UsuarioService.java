package com.backend.cloudclinicas.model.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.cloudclinicas.model.dao.IUsuario;
import com.backend.cloudclinicas.model.domain.Roles;
import com.backend.cloudclinicas.model.domain.Usuario;


@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {
	
	

	private Logger login = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuario usuarioDao;
	
	
	//Método de la interfaz UserDetailsService para buscar el usuario de la base de datos por nombre.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findBynombre(username);
		
		if(usuario == null) {
			login.error("Usuario no encontrado");
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for(Roles rol: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre_role()) );
		}
		
		return new User(usuario.getNombre(), usuario.getContrasena(), usuario.getHabilitado(), true, true, true, roles );
				
	}


}


