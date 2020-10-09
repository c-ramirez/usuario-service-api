package org.banxico.dgti.comparadorapp.config.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	T processQuery(ResultSet result) throws SQLException;
}
