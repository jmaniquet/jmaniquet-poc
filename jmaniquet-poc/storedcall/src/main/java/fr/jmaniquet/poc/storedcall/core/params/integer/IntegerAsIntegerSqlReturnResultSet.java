package fr.jmaniquet.poc.storedcall.core.params.integer;

import org.springframework.jdbc.core.SqlReturnResultSet;

public class IntegerAsIntegerSqlReturnResultSet extends SqlReturnResultSet {
	
	public IntegerAsIntegerSqlReturnResultSet(String name) {
		super(name, new IntegerFunctionResultExtractor());
	}
}
