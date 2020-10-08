package com.usuario.demo.service;

import java.util.List;

import com.usuario.demo.repository.tipocredito.TipoCredito;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepository;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepositoryImpl;
import com.usuario.demo.service.exception.BussinesException;
import com.usuario.demo.service.exception.DatabaseException;

public class TipoCreditoService {
	TipoCreditoRepository tipoCreditoRepository;

	public TipoCreditoService() {
		tipoCreditoRepository = new TipoCreditoRepositoryImpl();
	}

	public List<TipoCredito> obtenerTiposCredito() throws BussinesException {
		try {
			return tipoCreditoRepository.obtenerTiposCredito();
		} catch (DatabaseException e) {
			throw new BussinesException(e.getMessage());
		}
	}
}
