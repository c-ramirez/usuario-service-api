package com.usuario.demo.repository.usuario;

import java.util.List;

public interface UsuarioRepository {
	public Usuario obtenerUsuario(String usuario, String clave);
	public List<Usuario> obtenerUsuarios();
}
