package fr.jmaniquet.poc.storedcall.core.params.varchar;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;

public class VarcharAsStringSqlOutParameter extends SqlOutParameter {

	public VarcharAsStringSqlOutParameter(String name) {
		super(name, Types.VARCHAR);
	}
}
