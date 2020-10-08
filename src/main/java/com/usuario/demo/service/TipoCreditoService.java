package com.usuario.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.usuario.demo.repository.dao.TipoCreditoRepository;
import com.usuario.demo.repository.entity.TipoCredito;
import com.usuario.demo.repository.impl.TipoCreditoRepositoryImpl;
import com.usuario.demo.service.exception.BussinesException;
import com.usuario.demo.service.util.Messages;

public class TipoCreditoService {
	TipoCreditoRepository tipoCreditoRepository;
	Messages messages;

	public TipoCreditoService() {
		tipoCreditoRepository = new TipoCreditoRepositoryImpl();
		messages = Messages.getInstance();
	}

	public List<TipoCredito> obtenerTiposCredito() throws BussinesException {
		try {
			return tipoCreditoRepository.findAll();
		} catch (SQLException e) {
			throw new BussinesException(messages.getMessage("error.database.general"));
		}
	}
}
