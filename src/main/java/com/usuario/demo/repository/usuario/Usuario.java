package com.usuario.demo.repository.usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
	Integer id;
	@NotNull(message = "El nombre de usuario es obligatorio")
	String usuario;
	@NotNull(message = "El nombre de usuario es obligatorio")
	@Size(min = 5, max = 9,  message = "La contraseña debe tener entre {min} y {max} caracteres")
	String clave;
	String nombres;
	String apellidos;
	String rol;
	Boolean activo;	
}
