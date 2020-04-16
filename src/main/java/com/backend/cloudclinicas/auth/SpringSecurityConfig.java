package com.backend.cloudclinicas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration 
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//En esta clase vamos a configurar los usuarios que vamos a utilizar.

	
	@Autowired 
	private UserDetailsService usuarioService;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Metodo que va a crear un usuario nuestro que va a sustituir el que tenemos por defecto.
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}
	
	//Especificamos cuales roles pueden acceder a las URL'S de la api. 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/clientes").permitAll()
		.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/clientes/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/clientes/{id}").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		
		
		
		/*
		 * .antMatchers("/api/clientes/**")
		 * .hasRole("ADMIN")
		.antMatchers("/api/**")
		.hasAnyRole("ADMIN", "USER");
		 */
		
		
		
	}

	
	
	
	
}
