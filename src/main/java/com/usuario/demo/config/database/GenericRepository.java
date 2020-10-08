package com.usuario.demo.config.database;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.usuario.demo.service.util.SqlUtil;

public abstract class GenericRepository<T> implements IGenericRepository<T> {
	protected Connection connection;
	protected SqlConnection sqlConnection;

	public GenericRepository() {
		sqlConnection = new SqlConnectionDatasource();
		try {
			connection = sqlConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	public void commit() throws SQLException {
		if (!connection.getAutoCommit()) {
			connection.commit();
			connection.setAutoCommit(true);
		}
		connection.close();
	}

	public void rollback() throws SQLException {
		if (!connection.getAutoCommit()) {
			connection.rollback();
			connection.setAutoCommit(true);
		}
		connection.close();
	}

	public T findById(String sql, Mapper<T> mapper, Object id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = SqlUtil.camelToSnake(getTableNameFromClass()).toUpperCase();
		final String findById = "SELECT * from " + tableName + " WHERE " + sql;
		try {
			statement = connection.prepareStatement(findById);
			statement.setObject(1, id);
			result = statement.executeQuery();
			return mapper.processQuery(result);
		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	public List<T> findAll(Mapper<List<T>> mapper) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = SqlUtil.camelToSnake(getTableNameFromClass()).toUpperCase();
		final String findAll = "SELECT * from " + tableName;
		try {
			statement = connection.prepareStatement(findAll);
			result = statement.executeQuery();
			return mapper.processQuery(result);
		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	public Integer save(String sql, Object... args) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			return statement.executeUpdate();
		} finally {
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	public Integer delete(String sql, Object... args) throws SQLException {
		PreparedStatement statement = null;
		String tableName = SqlUtil.camelToSnake(getTableNameFromClass()).toUpperCase();
		final String delete = "DELETE FROM " + tableName + " WHERE " + sql;
		try {
			statement = connection.prepareStatement(delete);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			return statement.executeUpdate();
		} finally {
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	public T makeQuery(String sql, Mapper<T> mapper, Object... args) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			result = statement.executeQuery();
			return mapper.processQuery(result);
		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	public List<T> makeQueryList(String sql, Mapper<List<T>> mapper, Object... args) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			result = statement.executeQuery();

			return mapper.processQuery(result);

		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	@Override
	public Integer count(String sql, Object... args) throws SQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = SqlUtil.camelToSnake(getTableNameFromClass()).toUpperCase();
		final String findById = "SELECT count(*) from " + tableName + " WHERE " + sql;
		try {
			statement = connection.prepareStatement(findById);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			result = statement.executeQuery();
			if (result.next())
				return result.getInt(1);
			return -1;
		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection.getAutoCommit())
				connection.close();
		}
	}

	private String getTableNameFromClass() {
		@SuppressWarnings("unchecked")
		Class<T> typeOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return typeOfT.getSimpleName();
	}
}
