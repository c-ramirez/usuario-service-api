package com.usuario.demo.config.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqlConnectionDatasource implements SqlConnection{
	final Logger log = LogManager.getLogger(SqlConnectionDatasource.class);
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
			log.error(ex);
		} catch (IOException e) {
			log.error(e);
		} 
		return connection;
	}
}
