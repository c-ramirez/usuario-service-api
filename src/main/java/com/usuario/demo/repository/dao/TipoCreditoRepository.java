package com.usuario.demo.repository.dao;

import java.sql.SQLException;
import java.util.List;

import com.usuario.demo.config.database.IGenericRepository;
import com.usuario.demo.repository.entity.TipoCredito;

public interface TipoCreditoRepository extends IGenericRepository<TipoCredito>{
	public List<TipoCredito> findAll() throws SQLException;
}
