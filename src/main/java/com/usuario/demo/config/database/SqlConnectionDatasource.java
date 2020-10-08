package com.usuario.demo.config.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlConnectionDatasource implements SqlConnection{
	private Connection connection = null;
	Properties properties = new Properties();
	public SqlConnectionDatasource() {
		super();
	}

	public synchronized Connection getConnection() {
		try {
			if (connection == null) {
				properties.load(SqlConnectionDatasource.class.getResourceAsStream("/app.properties"));
				String driver = properties.getProperty("database.driver");
				String username = properties.getProperty("database.username");
				String password = properties.getProperty("database.password");
				String url = properties.getProperty("database.url");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username, password);
			}
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return connection;
	}
}
