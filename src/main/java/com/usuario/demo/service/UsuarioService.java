package com.usuario.demo.service;

import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.repository.usuario.UsuarioRepository;
import com.usuario.demo.repository.usuario.UsuarioRepositoryImpl;

public class UsuarioService {
	UsuarioRepository usuarioRepository;
	

	public UsuarioService() {
		usuarioRepository = new UsuarioRepositoryImpl();
		
	}

	public Usuario login(Usuario usuario) throws Exception {
		return usuarioRepository.obtenerUsuario(usuario.getUsuario(), usuario.getClave());
	}


	public void crearUsuario(Usuario usuario) throws Exception {
		usuarioRepository.crear(usuario);
	}

	public Usuario obtenerUsuario(Integer id) throws Exception {
		return usuarioRepository.obtenerUsuarioPorId(id);
	}

	public Integer actualizarUsuario(Usuario usuario) throws Exception {
		return usuarioRepository.actualizar(usuario);
	}

	public Integer eliminarUsuario(Integer id) throws Exception {
		return usuarioRepository.eliminar(id);
	}
}
