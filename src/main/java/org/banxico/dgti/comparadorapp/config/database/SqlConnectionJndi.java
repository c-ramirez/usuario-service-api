package org.banxico.dgti.comparadorapp.config.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqlConnectionJndi implements SqlConnection{
	final Logger log = LogManager.getLogger(SqlConnectionJndi.class);
	private Connection connection = null;
	Properties properties = new Properties();
	public SqlConnectionJndi() {
		super();
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		try {
			if (connection == null) {
				properties.load(SqlConnectionDatasource.class.getResourceAsStream("/app.properties"));
				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext.lookup(properties.getProperty("database.jndi"));
				connection = ds.getConnection();
			}
		}  catch (NamingException | IOException e) {
			log.error(e);
		} 
		return connection;
	}

}
