package com.usuario.demo.repository.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usuario.demo.repository.validationgroup.Login;
import com.usuario.demo.repository.validationgroup.Register;


public class Usuario {
	Integer id;
	@NotNull(message = "{usuario.usuario.notNull}", groups = {Login.class,Register.class})
	String usuario;
	@NotNull(message = "{usuario.clave.notNull}", groups = {Login.class,Register.class})
	@Size(min = 8, max = 32,  message = "{usuario.clave.size}", groups = {Login.class,Register.class})
	String clave;
	@NotNull(message = "{usuario.nombres.notNull}", groups = {Register.class})
	String nombres;
	@NotNull(message = "{usuario.apellidos.notNull}", groups = {Register.class})
	String apellidos;
	String rol;
	Boolean activo;
	
	@JsonIgnore
	public boolean isEmpty() {
		return id == null;
	}
	@JsonIgnore
	public String getFullName() {
		return nombres + apellidos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}	
	
}
