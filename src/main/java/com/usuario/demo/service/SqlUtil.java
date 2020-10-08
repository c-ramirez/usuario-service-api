package com.usuario.demo.service;

import java.sql.SQLException;

import com.usuario.demo.service.exception.SqlExceptionType;

public class SqlUtil {
	private SqlUtil() {
	}
	public static String camelToSnake(String name) {
		return name.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
	}
	public static SqlExceptionType typeOfException(SQLException e) {
		return SqlExceptionType.getTypeFromException(e.getSQLState().substring(0, 2));
	}
	public static boolean isConstraintViolation(SQLException e) {
	    return e.getSQLState().startsWith("23");
	}
}
