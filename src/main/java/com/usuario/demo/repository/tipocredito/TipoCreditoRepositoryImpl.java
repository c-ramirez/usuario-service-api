package com.usuario.demo.repository.tipocredito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.repository.GenericRepository;
import com.usuario.demo.service.exception.DatabaseException;

public class TipoCreditoRepositoryImpl extends GenericRepository<TipoCredito> implements TipoCreditoRepository {

	@Override
	public List<TipoCredito> obtenerTiposCredito() throws DatabaseException {
		try {
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
		} catch (SQLException e) {
			throw new DatabaseException("Ocurrio un error desconocido");
		}

	}

}
