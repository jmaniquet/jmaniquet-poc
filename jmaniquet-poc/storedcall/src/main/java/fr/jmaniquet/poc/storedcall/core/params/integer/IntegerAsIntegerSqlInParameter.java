package fr.jmaniquet.poc.storedcall.core.params.integer;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;

public class IntegerAsIntegerSqlInParameter extends SqlParameter {

	public IntegerAsIntegerSqlInParameter(String name) {
		super(name, Types.INTEGER);
	}
}
