package com.usuario.demo.repository.usuario;

import java.util.List;

public interface UsuarioRepository extends IGenericRepository<Usuario>{
	public Usuario obtenerUsuario(String usuario, String clave);
	public List<Usuario> obtenerUsuarios();
	public Usuario obtenerUsuarioPorId(Integer id);
	public void actualizar(Usuario usuario);
	public void eliminar(Integer id);
	public void crear(Usuario usuario);
}
