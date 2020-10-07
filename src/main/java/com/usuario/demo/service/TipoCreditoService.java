package com.usuario.demo.service;

import java.util.List;

import com.usuario.demo.repository.tipocredito.TipoCredito;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepository;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepositoryImpl;

public class TipoCreditoService {
	TipoCreditoRepository tipoCreditoRepository;
	public TipoCreditoService() {
		tipoCreditoRepository  = new TipoCreditoRepositoryImpl();
	}
	public List<TipoCredito> obtenerTiposCredito() throws Exception{
		return tipoCreditoRepository.obtenerTiposCredito();
	}
}
