package com.usuario.demo.repository;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.usuario.demo.repository.usuario.IGenericRepository;


public abstract class GenericRepository<T> implements IGenericRepository<T> {
	protected Connection connection;
	protected SqlConnection sqlConnection;

	public GenericRepository() {
		sqlConnection = new SqlConnectionDatasource();
		connection = sqlConnection.getConnection();
	}

	public T findById(String sql, Mapper<T> mapper, Object id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = camelToSnake(getTableNameFromClass()).toUpperCase();
		final String findById = "SELECT * from " + tableName + " WHERE " + sql;
		try {
			statement = connection.prepareStatement(findById);
			statement.setObject(1, id);
			result = statement.executeQuery();
			return mapper.processQuery(result);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
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

	public List<T> findAll(Mapper<List<T>> mapper) {
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = camelToSnake(getTableNameFromClass()).toUpperCase();
		final String findAll = "SELECT * from " + tableName;
		try {
			statement = connection.prepareStatement(findAll);
			result = statement.executeQuery();
			return mapper.processQuery(result);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
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

	public void save(String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
				try {
					statement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void update(String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
				try {
					statement.close();

					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void delete(String sql, Object... args) {
		PreparedStatement statement = null;
		String tableName = camelToSnake(getTableNameFromClass()).toUpperCase();
		final String delete = "DELETE FROM " + tableName + " WHERE " + sql;
		try {
			statement = connection.prepareStatement(delete);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
				try {
					statement.close();

					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
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

	private String getTableNameFromClass() {
		@SuppressWarnings("unchecked")
		Class<T> typeOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return typeOfT.getSimpleName();
	}

	private String camelToSnake(String name) {
		return name.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
	}
}
