package com.usuario.demo.service;

import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.repository.usuario.UsuarioRepository;
import com.usuario.demo.repository.usuario.UsuarioRepositoryImpl;
import com.usuario.demo.service.exception.BussinesException;
import com.usuario.demo.service.exception.DatabaseException;

public class UsuarioService {
	UsuarioRepository usuarioRepository;

	public UsuarioService() {
		usuarioRepository = new UsuarioRepositoryImpl();

	}

	public Usuario login(Usuario usuario) throws BussinesException {
		try {
			Usuario usuarioDB = usuarioRepository.obtenerUsuario(usuario.getUsuario(), usuario.getClave());
			if (usuarioDB.getId() == null)
				throw new BussinesException("No se encontro al usuario con nombre:" +usuario.getUsuario());
			return usuarioDB;

		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}

	public void crearUsuario(Usuario usuario) throws BussinesException {
		try {
			usuarioRepository.crear(usuario);
		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}

	public Usuario obtenerUsuario(Integer id) throws BussinesException {
		try {
			Usuario usuario = usuarioRepository.obtenerUsuarioPorId(id);
			if (usuario.getId() == null)
				throw new BussinesException("No se encontro al usuario con ID:" + id);
			return usuario;
		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}

	public Integer actualizarUsuario(Usuario usuario) throws BussinesException {
		try {
			return usuarioRepository.actualizar(usuario);
		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}

	public void eliminarUsuario(Integer id) throws BussinesException {
		try {
			Integer registrosEliminados = usuarioRepository.eliminar(id);
			if (registrosEliminados == 0)
				throw new BussinesException("No se eliminó ningun usuario");
		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}
}
