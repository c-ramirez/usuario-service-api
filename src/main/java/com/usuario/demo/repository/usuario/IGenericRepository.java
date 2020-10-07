package com.usuario.demo.repository.usuario;

import java.sql.SQLException;
import java.util.List;

import com.usuario.demo.repository.Mapper;

public interface IGenericRepository<T> {
	T findById(String sql, Mapper<T> mapper, Object id)  throws SQLException;
	List<T> findAll(Mapper<List<T>> mapper) throws SQLException;
	Integer save(String sql, Object ... args) throws SQLException;
	Integer update(String sql, Object... args) throws SQLException;
	Integer delete(String sql, Object... args) throws SQLException;
	T makeQuery(String sql, Mapper<T> mapper, Object... args) throws SQLException;
	List<T> makeQueryList(String sql, Mapper<List<T>> mapper, Object... args) throws SQLException;
}
