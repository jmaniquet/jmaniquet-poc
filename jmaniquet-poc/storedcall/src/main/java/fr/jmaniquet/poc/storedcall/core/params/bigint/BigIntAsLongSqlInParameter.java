package fr.jmaniquet.poc.storedcall.core.params.bigint;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;

public class BigIntAsLongSqlInParameter extends SqlParameter {

	public BigIntAsLongSqlInParameter(String name) {
		super(name, Types.BIGINT);
	}
}
