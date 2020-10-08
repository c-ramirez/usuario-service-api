package com.usuario.demo.repository.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.config.database.GenericRepository;
import com.usuario.demo.repository.dao.UsuarioRepository;
import com.usuario.demo.repository.entity.Usuario;
import com.usuario.demo.service.util.Messages;

public class UsuarioRepositoryImpl extends GenericRepository<Usuario> implements UsuarioRepository {
	Messages messages = Messages.getInstance();

	public Usuario findByUsuarioAndClave(String usuario, String clave) throws SQLException {
		return makeQuery("SELECT * from usuario WHERE usuario=? AND clave=?", result -> {
			Usuario usuarioDB = new Usuario();
			while (result.next()) {
				usuarioDB.setId(result.getInt("id"));
				usuarioDB.setUsuario(result.getString("usuario"));
				usuarioDB.setClave(result.getString("clave"));
				usuarioDB.setNombres(result.getString("nombres"));
				usuarioDB.setApellidos(result.getString("apellidos"));
				usuarioDB.setRol(result.getString("rol"));
				usuarioDB.setActivo(result.getBoolean("activo"));
			}
			return usuarioDB;
		}, usuario, clave);
	}

	public List<Usuario> findAll() throws SQLException {
		return findAll(result -> {
			List<Usuario> usuarios = new ArrayList<>();
			while (result.next()) {
				Usuario usuarioDB = new Usuario();
				usuarioDB.setId(result.getInt("id"));
				usuarioDB.setUsuario(result.getString("usuario"));
				usuarioDB.setClave(result.getString("clave"));
				usuarioDB.setNombres(result.getString("nombres"));
				usuarioDB.setApellidos(result.getString("apellidos"));
				usuarioDB.setRol(result.getString("rol"));
				usuarioDB.setActivo(result.getBoolean("activo"));
				usuarios.add(usuarioDB);
			}
			return usuarios;
		});
	}

	@Override
	public Usuario findById(Integer id) throws SQLException {
		return findById("id = ?", result -> {
			Usuario usuarioDB = new Usuario();
			while (result.next()) {
				usuarioDB.setId(result.getInt("id"));
				usuarioDB.setUsuario(result.getString("usuario"));
				usuarioDB.setClave(result.getString("clave"));
				usuarioDB.setNombres(result.getString("nombres"));
				usuarioDB.setApellidos(result.getString("apellidos"));
				usuarioDB.setRol(result.getString("rol"));
				usuarioDB.setActivo(result.getBoolean("activo"));
			}
			return usuarioDB;
		}, id);
	}

	@Override
	public Integer update(Usuario usuario) throws SQLException {
		return save("UPDATE Usuario set nombres=?,apellidos=? WHERE id =?", usuario.getNombres(),
				usuario.getApellidos(), usuario.getId());
	}

	@Override
	public Integer delete(Integer id) throws SQLException {
		return delete("id=?", id);
	}

	@Override
	public Integer save(Usuario usuario) throws SQLException {
		return save("INSERT INTO USUARIO VALUES (?,?,?,?,?,?)", usuario.getUsuario(), usuario.getClave(),
				usuario.getNombres(), usuario.getApellidos(), usuario.getRol(), usuario.getActivo());
	}

	@Override
	public Usuario findByUsuario(String usuario) throws SQLException {
		return makeQuery("SELECT * from usuario WHERE usuario=?", result -> {
			Usuario usuarioDB = new Usuario();
			while (result.next()) {
				usuarioDB.setId(result.getInt("id"));
				usuarioDB.setUsuario(result.getString("usuario"));
				usuarioDB.setClave(result.getString("clave"));
				usuarioDB.setNombres(result.getString("nombres"));
				usuarioDB.setApellidos(result.getString("apellidos"));
				usuarioDB.setRol(result.getString("rol"));
				usuarioDB.setActivo(result.getBoolean("activo"));
			}
			return usuarioDB;
		}, usuario);
	}

	@Override
	public Integer countByUsuario(String usuario) throws SQLException {
		return count("usuario = ?", usuario);
	}

}
