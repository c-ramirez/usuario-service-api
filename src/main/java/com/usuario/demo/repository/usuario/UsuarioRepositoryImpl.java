package com.usuario.demo.repository.usuario;

import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.repository.GenericRepository;

public class UsuarioRepositoryImpl extends GenericRepository<Usuario> implements UsuarioRepository {

	public Usuario obtenerUsuario(String usuario, String clave) {
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

	public List<Usuario> obtenerUsuarios() {
//		return makeQueryList("SELECT * from usuario ", (result) -> {
//			List<Usuario> usuarios = new ArrayList<>();
//			while (result.next()) {
//				Usuario usuarioDB = new Usuario();
//				usuarioDB.setId(result.getInt("id"));
//				usuarioDB.setUsuario(result.getString("usuario"));
//				usuarioDB.setClave(result.getString("clave"));
//				usuarioDB.setNombres(result.getString("nombres"));
//				usuarioDB.setApellidos(result.getString("apellidos"));
//				usuarioDB.setRol(result.getString("rol"));
//				usuarioDB.setActivo(result.getBoolean("activo"));
//				usuarios.add(usuarioDB);
//			}
//			return usuarios;
//		});
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
	public Usuario obtenerUsuarioPorId(Integer id) {
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
	}

	@Override
	public void actualizar(Usuario usuario) {
		update("UPDATE Usuario set nombres=?,apellidos=? WHERE id =?", usuario.getNombres(), usuario.getApellidos(),
				usuario.getId());

	}

	@Override
	public void eliminar(Integer id) {
		delete("id=?", id);
		
	}

	@Override
	public void crear(Usuario usuario) {
		save("INSERT INTO USUARIO VALUES (?,?,?,?,?,?)", usuario.getUsuario(), usuario.getClave(),
				usuario.getNombres(), usuario.getApellidos(), usuario.getRol(), usuario.getActivo());
		
	}

}
