package com.usuario.demo.repository.usuario;

import java.util.List;

import com.usuario.demo.repository.Mapper;

public interface IGenericRepository<T> {
	T findById(String sql, Mapper<T> mapper, Object id);
	List<T> findAll(Mapper<List<T>> mapper);
	void save(String sql, Object ... args);
	void update(String sql, Object... args);
	void delete(String sql, Object... args);
	T makeQuery(String sql, Mapper<T> mapper, Object... args);
	List<T> makeQueryList(String sql, Mapper<List<T>> mapper, Object... args);
}
