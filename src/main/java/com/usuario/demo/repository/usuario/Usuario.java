package com.usuario.demo.repository.usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Usuario {
	Integer id;
	@NotNull(message = "El nombre de usuario es obligatorio")
	String usuario;
	@NotNull(message = "La clave es obligatorio")
	@Size(min = 8, max = 32,  message = "La clave debe tener entre {min} y {max} caracteres")
	String clave;
	String nombres;
	String apellidos;
	String rol;
	Boolean activo;
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
