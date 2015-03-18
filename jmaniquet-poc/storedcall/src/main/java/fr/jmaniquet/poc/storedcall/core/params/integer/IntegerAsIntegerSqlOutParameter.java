package fr.jmaniquet.poc.storedcall.core.params.integer;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;

public class IntegerAsIntegerSqlOutParameter extends SqlOutParameter {

	public IntegerAsIntegerSqlOutParameter(String name) {
		super(name, Types.INTEGER);
	}
}
