package org.banxico.dgti.comparadorapp.repository.dao;

import java.sql.SQLException;
import java.util.List;

import org.banxico.dgti.comparadorapp.config.database.IGenericRepository;
import org.banxico.dgti.comparadorapp.repository.entity.TipoCredito;

public interface TipoCreditoRepository extends IGenericRepository<TipoCredito>{
	public List<TipoCredito> findAll() throws SQLException;
}
