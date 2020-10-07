package com.usuario.demo.repository.usuario;

import java.util.List;

public interface UsuarioRepository extends IGenericRepository<Usuario>{
	public Usuario obtenerUsuario(String usuario, String clave) throws Exception;
	public List<Usuario> obtenerUsuarios() throws Exception;
	public Usuario obtenerUsuarioPorId(Integer id) throws Exception;
	public Integer actualizar(Usuario usuario) throws Exception;
	public Integer eliminar(Integer id) throws Exception;
	public Integer crear(Usuario usuario) throws Exception;
}
