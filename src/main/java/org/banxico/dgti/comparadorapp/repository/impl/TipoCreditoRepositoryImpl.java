package org.banxico.dgti.comparadorapp.repository.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.banxico.dgti.comparadorapp.config.database.GenericRepository;
import org.banxico.dgti.comparadorapp.repository.dao.TipoCreditoRepository;
import org.banxico.dgti.comparadorapp.repository.entity.TipoCredito;

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
