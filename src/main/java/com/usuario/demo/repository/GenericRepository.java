package com.usuario.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.java.Log;

@Log
public abstract class GenericRepository<T> {
	protected Connection connection;
	protected SqlConnection sqlConnection;

	public GenericRepository() {
		sqlConnection = new SqlConnectionDatasource();
		connection = sqlConnection.getConnection();
	}

	public T makeQuery(String sql, Mapper<T> mapper, Object... args) {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			result = statement.executeQuery();

			return mapper.processQuery(result);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			log.info("Cerrando conexion");
			if (result != null)
				try {
					result.close();
					if (statement != null)
						statement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public List<T> makeQueryList(String sql, Mapper<List<T>> mapper, Object... args) {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			result = statement.executeQuery();

			return mapper.processQuery(result);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			log.info("Cerrando conexion");
			if (result != null)
				try {
					result.close();
					if (statement != null)
						statement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
