package com.usuario.demo.repository.tipocredito;

import java.util.List;

import com.usuario.demo.repository.usuario.IGenericRepository;

public interface TipoCreditoRepository extends IGenericRepository<TipoCredito>{
	public List<TipoCredito> obtenerTiposCredito() throws Exception;
}
