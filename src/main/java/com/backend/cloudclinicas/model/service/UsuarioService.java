package com.backend.cloudclinicas.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.backend.cloudclinicas.model.dao.IUsuario;
import com.backend.cloudclinicas.model.domain.Usuario;

public class UsuarioService implements UserDetailsService {

	@Autowired
	private IUsuario usuarioDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findBynombre(username);
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre_role()))
				.collect(Collectors.toList());
		return new User(usuario.getNombre(), usuario.getContrasena(), usuario.getHabilitado(), true, true, true, authorities );
	}

}
