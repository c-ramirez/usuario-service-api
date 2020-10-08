package com.usuario.demo.repository.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.config.database.GenericRepository;
import com.usuario.demo.repository.dao.TipoCreditoRepository;
import com.usuario.demo.repository.entity.TipoCredito;

public class TipoCreditoRepositoryImpl extends GenericRepository<TipoCredito> implements TipoCreditoRepository {

	@Override
	public List<TipoCredito> findAll() throws SQLException {
		return makeQueryList("SELECT * from tipo_credito", result -> {
			List<TipoCredito> tiposCredito = new ArrayList<>();
			while (result.next()) {
				TipoCredito tipo = new TipoCredito();
				tipo.setId(result.getInt("id"));
				tipo.setNombre(result.getString("nombre"));
				tiposCredito.add(tipo);
			}
			return tiposCredito;
		});
	}

}
