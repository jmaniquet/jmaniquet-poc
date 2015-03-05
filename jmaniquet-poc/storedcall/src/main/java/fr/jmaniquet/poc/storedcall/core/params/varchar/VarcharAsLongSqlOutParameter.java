package fr.jmaniquet.poc.storedcall.core.params.varchar;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;

public class VarcharAsLongSqlOutParameter extends SqlOutParameter {

	public VarcharAsLongSqlOutParameter(String name) {
		super(name, Types.VARCHAR);
	}
}
