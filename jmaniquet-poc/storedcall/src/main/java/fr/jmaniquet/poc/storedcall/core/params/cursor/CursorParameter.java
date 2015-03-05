package fr.jmaniquet.poc.storedcall.core.params.cursor;

import org.springframework.jdbc.core.RowMapper;

public class CursorParameter<T> {
	
	private String parameterName;
	private RowMapper<T> rowMapper;
	
	public CursorParameter(String parameterName, RowMapper<T> rowMapper) {
		super();
		this.parameterName = parameterName;
		this.rowMapper = rowMapper;
	}
	
	public String getParameterName() {
		return parameterName;
	}
	public RowMapper<T> getRowMapper() {
		return rowMapper;
	}
}
