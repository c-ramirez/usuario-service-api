package com.usuario.demo.repository.tipocredito;

import java.util.ArrayList;
import java.util.List;

import com.usuario.demo.repository.GenericRepository;

public class TipoCreditoRepositoryImpl extends GenericRepository<TipoCredito> implements TipoCreditoRepository{

	@Override
	public List<TipoCredito> obtenerTiposCredito() {
		return makeQueryList("SELECT * from tipo_credito", (result) -> {
			List<TipoCredito> tiposCredito = new ArrayList<>();
			while(result.next()) {
				TipoCredito tipo = new TipoCredito();
				tipo.setId(result.getInt("id"));
				tipo.setNombre(result.getString("nombre"));
				tiposCredito.add(tipo);
			}
			return tiposCredito;
		});
	}

}
