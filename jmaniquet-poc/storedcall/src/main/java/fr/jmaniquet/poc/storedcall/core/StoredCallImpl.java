package fr.jmaniquet.poc.storedcall.core;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StoredCallImpl implements StoredCall {
	
	private String procedureName;
	private SqlParameter [] parameters;
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall call;
	
	public StoredCallImpl() {
		parameters = new SqlParameter[0];
	}
	
	@PostConstruct
	public void postConstruct() {
		SimpleJdbcCall theCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName)
				.declareParameters(parameters);
		
		this.call = theCall;
	}
	
	public StoredCallImpl(SimpleJdbcCall call) {
		this.call = call;
	}
	
	@Override
	public StoredCallResult execute(Object... args) {		
		Map<String, Object> resultMap = call.execute(args);
		return new StoredCallResult(resultMap);
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public SqlParameter[] getParameters() {
		return parameters;
	}

	public void setParameters(SqlParameter[] parameters) {
		this.parameters = parameters;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
