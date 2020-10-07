package com.usuario.demo.repository.tipocredito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.repository.GenericRepository;
import com.usuario.demo.service.SqlUtil;
import com.usuario.demo.service.exception.UniqueConstraintException;

public class TipoCreditoRepositoryImpl extends GenericRepository<TipoCredito> implements TipoCreditoRepository {

	@Override
	public List<TipoCredito> obtenerTiposCredito() throws Exception {
		try {
			return makeQueryList("SELECT * from tipo_credito", (result) -> {
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
			switch (SqlUtil.typeOfException(e)) {
			case INTEGRITY_CONSTRAINT_VIOLATION:
				throw new UniqueConstraintException("El nombre de usuario ya existe.");

			default:
				throw new Exception("Ocurrio un error desconocido");
			}
		}

	}

}
