package com.usuario.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	T processQuery(ResultSet result) throws SQLException;
}
