package org.banxico.dgti.comparadorapp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.banxico.dgti.comparadorapp.repository.dao.UsuarioRepository;
import org.banxico.dgti.comparadorapp.repository.entity.Usuario;
import org.banxico.dgti.comparadorapp.repository.impl.UsuarioRepositoryImpl;
import org.banxico.dgti.comparadorapp.service.exception.BussinesException;
import org.banxico.dgti.comparadorapp.service.exception.SqlExceptionType;
import org.banxico.dgti.comparadorapp.service.util.Messages;
import org.banxico.dgti.comparadorapp.service.util.SqlUtil;

public class UsuarioService {
	final Logger log = LogManager.getLogger(UsuarioService.class);
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
				log.error("Error en login ",e);
				throw new BussinesException(messages.getMessage("error.database.general"));
			}
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void save(Usuario usuario) throws BussinesException {
		try {
			usuarioRepository.save(usuario);
		} catch (SQLException e) {
			log.error("Error en save ",e);
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
			log.error("Error en findById ",e);
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void update(Usuario usuario) throws BussinesException {
		try {
			Integer affectedRows = usuarioRepository.update(usuario);
			if (affectedRows == 0)
				throw new BussinesException(messages.getMessage("error.database.entity.update"));
		} catch (SQLException e) {
			log.error("Error en update ",e);
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}

	public void delete(Integer id) throws BussinesException {
		try {
			Integer affectedRows = usuarioRepository.delete(id);
			if (affectedRows == 0)
				throw new BussinesException(messages.getMessage("error.database.entity.delete"));
		} catch (SQLException e) {
			log.error("Error en delete ",e);
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}
	public List<Usuario> findAll() throws BussinesException {
		try {
			return usuarioRepository.findAll();
		} catch (SQLException e) {
			log.error("Error en findAll ",e);
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}
}
