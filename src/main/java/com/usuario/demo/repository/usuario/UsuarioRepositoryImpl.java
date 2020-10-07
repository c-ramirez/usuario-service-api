package com.usuario.demo.repository.usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.repository.GenericRepository;
import com.usuario.demo.service.SqlUtil;
import com.usuario.demo.service.exception.UniqueConstraintException;

public class UsuarioRepositoryImpl extends GenericRepository<Usuario> implements UsuarioRepository {

	public Usuario obtenerUsuario(String usuario, String clave) throws Exception {
		return makeQuery("SELECT * from usuario WHERE usuario=? AND clave=?", (result) -> {
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

	public List<Usuario> obtenerUsuarios() throws Exception {
		return findAll((result) -> {
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
	public Usuario obtenerUsuarioPorId(Integer id) throws Exception {
		try {
			return findById("id = ?", (result) -> {
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
		} catch (SQLException e) {
			switch (SqlUtil.typeOfException(e)) {
			case INTEGRITY_CONSTRAINT_VIOLATION:
				throw new UniqueConstraintException("El nombre de usuario ya existe.");
			default:
				throw new Exception("Ocurrio un error desconocido");
			}
		}

	}

	@Override
	public Integer actualizar(Usuario usuario) throws Exception {
		try {
			return update("UPDATE Usuario set nombres=?,apellidos=? WHERE id =?", usuario.getNombres(),
					usuario.getApellidos(), usuario.getId());
		} catch (SQLException e) {
			switch (SqlUtil.typeOfException(e)) {
			case INTEGRITY_CONSTRAINT_VIOLATION:
				throw new UniqueConstraintException("El nombre de usuario ya existe.");

			default:
				throw new Exception("Ocurrio un error desconocido");
			}
		}

	}

	@Override
	public Integer eliminar(Integer id) throws Exception {
		try {
			return delete("id=?", id);
		} catch (SQLException e) {
			switch (SqlUtil.typeOfException(e)) {
			case INTEGRITY_CONSTRAINT_VIOLATION:
				throw new UniqueConstraintException("El nombre de usuario ya existe.");

			default:
				throw new Exception("Ocurrio un error desconocido");
			}
		}

	}

	@Override
	public Integer crear(Usuario usuario) throws Exception {
		try {
			return save("INSERT INTO USUARIO VALUES (?,?,?,?,?,?)", usuario.getUsuario(), usuario.getClave(),
					usuario.getNombres(), usuario.getApellidos(), usuario.getRol(), usuario.getActivo());
		} catch (SQLException e) {
			switch (SqlUtil.typeOfException(e)) {
			case INTEGRITY_CONSTRAINT_VIOLATION:
				throw new UniqueConstraintException("El nombre de usuario ya existe.");

			default:
				throw new Exception("Ocurrio un error desconocido");
			}
		}

	}

}
