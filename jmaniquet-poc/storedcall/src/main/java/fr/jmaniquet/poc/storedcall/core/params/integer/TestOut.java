package fr.jmaniquet.poc.storedcall.core.params.integer;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;

public class TestOut extends SqlOutParameter {

	public TestOut(String name) {
		super(name, Types.INTEGER);
	}
	
	public boolean isResultsParameter() {
		return true;
	}
}
