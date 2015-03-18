package fr.jmaniquet.poc.storedcall.core.params.integer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class IntegerFunctionResultExtractor implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		rs.next();
		int int1 = rs.getInt(1);
		if (rs.wasNull()) {
			return null;
		}
		return int1;
	}

	
}