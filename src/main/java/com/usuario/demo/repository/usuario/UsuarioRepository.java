package com.usuario.demo.repository.usuario;

import java.util.List;

import com.usuario.demo.service.exception.DatabaseException;

public interface UsuarioRepository extends IGenericRepository<Usuario>{
	public Usuario obtenerUsuario(String usuario, String clave) throws DatabaseException;
	public List<Usuario> obtenerUsuarios() throws DatabaseException;
	public Usuario obtenerUsuarioPorId(Integer id) throws DatabaseException;
	public Integer actualizar(Usuario usuario) throws DatabaseException;
	public Integer eliminar(Integer id) throws DatabaseException;
	public Integer crear(Usuario usuario) throws DatabaseException;
}
