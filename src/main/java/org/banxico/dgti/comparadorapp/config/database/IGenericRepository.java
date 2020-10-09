package org.banxico.dgti.comparadorapp.config.database;

import java.sql.SQLException;
import java.util.List;

public interface IGenericRepository<T> {
	T findById(String sql, Mapper<T> mapper, Object id)  throws SQLException;
	List<T> findAll(Mapper<List<T>> mapper) throws SQLException;
	Integer save(String sql, Object ... args) throws SQLException;
	Integer delete(String sql, Object... args) throws SQLException;
	T makeQuery(String sql, Mapper<T> mapper, Object... args) throws SQLException;
	List<T> makeQueryList(String sql, Mapper<List<T>> mapper, Object... args) throws SQLException;
	Integer count(String sql, Object... args)throws SQLException;
	void startTransaction() throws SQLException;
	void commit()  throws SQLException;
	void rollback() throws SQLException ;
}
