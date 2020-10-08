package com.usuario.demo.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnection {
	Connection getConnection() throws SQLException;
}
