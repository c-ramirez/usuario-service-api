package org.banxico.dgti.comparadorapp.repository.dao;

import java.sql.SQLException;
import java.util.List;

import org.banxico.dgti.comparadorapp.config.database.IGenericRepository;
import org.banxico.dgti.comparadorapp.repository.entity.Usuario;

public interface UsuarioRepository extends IGenericRepository<Usuario>{
	public Usuario findByUsuarioAndClave(String usuario, String clave) throws SQLException;
	public Usuario findByUsuario(String usuario) throws SQLException;
	public List<Usuario> findAll() throws SQLException;
	public Usuario findById(Integer id) throws SQLException;
	public Integer update(Usuario usuario) throws SQLException;
	public Integer delete(Integer id) throws SQLException;
	public Integer save(Usuario usuario) throws SQLException;
	public Integer countByUsuario(String Usuario) throws SQLException;
}
