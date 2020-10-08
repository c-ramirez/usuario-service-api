package com.usuario.demo.service;

import java.sql.SQLException;

import com.usuario.demo.repository.dao.UsuarioRepository;
import com.usuario.demo.repository.entity.Usuario;
import com.usuario.demo.repository.impl.UsuarioRepositoryImpl;
import com.usuario.demo.service.exception.BussinesException;
import com.usuario.demo.service.exception.SqlExceptionType;
import com.usuario.demo.service.util.Messages;
import com.usuario.demo.service.util.SqlUtil;

public class UsuarioService {
	UsuarioRepository usuarioRepository;
	Messages messages;

	public UsuarioService() {
		usuarioRepository = new UsuarioRepositoryImpl();
		messages = Messages.getInstance();
	}

	public Usuario login(Usuario usuario) throws BussinesException {

		try {
			usuarioRepository.startTransaction();
			Integer userCount = usuarioRepository.countByUsuario(usuario.getUsuario());
			if (userCount == 0)
				throw new BussinesException(
						messages.getMessage("error.database.usuario.usuario.notFound", usuario.getUsuario()));
			Usuario usuarioDB = usuarioRepository.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave());
			if (usuarioDB.isEmpty())
				throw new BussinesException(
						messages.getMessage("error.database.usuario.password.notFound", usuario.getUsuario()));

			usuarioRepository.commit();
			return usuarioDB;
		} catch (SQLException ex) {
			try {
				usuarioRepository.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BussinesException(messages.getMessage("error.database.general"));
			}
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void save(Usuario usuario) throws BussinesException {
		try {
			usuarioRepository.save(usuario);
		} catch (SQLException e) {
			SqlExceptionType type = SqlUtil.typeOfException(e);
			if (type == SqlExceptionType.INTEGRITY_CONSTRAINT_VIOLATION)
				throw new BussinesException(messages.getMessage("error.database.usuario.usuario.unique"));
			else
				throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public Usuario findById(Integer id) throws BussinesException {
		try {
			Usuario usuario = usuarioRepository.findById(id);
			if (usuario.isEmpty())
				throw new BussinesException(messages.getMessage("error.database.usuario.id.notFound", id));
			return usuario;
		} catch (SQLException e) {
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void update(Usuario usuario) throws BussinesException {
		try {
			Integer affectedRows = usuarioRepository.update(usuario);
			if (affectedRows == 0)
				throw new BussinesException(messages.getMessage("error.database.usuario.update"));
		} catch (SQLException e) {
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void delete(Integer id) throws BussinesException {
		try {
			Integer affectedRows = usuarioRepository.delete(id);
			if (affectedRows == 0)
				throw new BussinesException(messages.getMessage("error.database.usuario.delete"));
		} catch (SQLException e) {
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}
}
