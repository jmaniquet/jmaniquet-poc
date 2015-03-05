package fr.jmaniquet.poc.storedcall.core.params.bigint;

import java.sql.Types;

import org.springframework.jdbc.core.SqlInOutParameter;

public class BigIntAsLongSqlInOutParameter extends SqlInOutParameter {

	public BigIntAsLongSqlInOutParameter(String name) {
		super(name, Types.BIGINT);
	}
}
