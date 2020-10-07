package com.usuario.demo.service.exception;

public enum SqlExceptionType {
	NO_DATA("02"), DYNAMIC_SQL_ERROR("07"), CONNECTION_EXCEPTION("08"), FEATURE_NOT_SUPPORTED("0A"),
	CARDINALITY_VIOLATION("21"), DATA_EXCEPTION("22"), INTEGRITY_CONSTRAINT_VIOLATION("23"),
	INVALID_CURSOR_STATE("24"), INVALID_TRANSACTION_STATE("25"), INVALID_SQL_STATEMENT_NAME("26"),
	INVALID_AUTHORIZATION_SPECIFICATION("28");

	String code;

	private SqlExceptionType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static SqlExceptionType getTypeFromException(String status) {
		for(SqlExceptionType type : SqlExceptionType.values()) {
			if(type.getCode().equals(status))return type;
		}
		return null;
	}
}
