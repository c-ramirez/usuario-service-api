package org.banxico.dgti.comparadorapp.service;

import java.sql.SQLException;
import java.util.List;

import org.banxico.dgti.comparadorapp.repository.dao.TipoCreditoRepository;
import org.banxico.dgti.comparadorapp.repository.entity.TipoCredito;
import org.banxico.dgti.comparadorapp.repository.impl.TipoCreditoRepositoryImpl;
import org.banxico.dgti.comparadorapp.service.exception.BussinesException;
import org.banxico.dgti.comparadorapp.service.util.Messages;

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
