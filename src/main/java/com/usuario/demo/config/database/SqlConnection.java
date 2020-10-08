package com.usuario.demo.config.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnection {
	Connection getConnection() throws SQLException;
}
