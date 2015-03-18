package fr.jmaniquet.poc.storedcall.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import fr.jmaniquet.poc.storedcall.core.params.cursor.CursorParameter;
import fr.jmaniquet.poc.storedcall.core.params.timestamp.TimestampAsDateTimeSqlValue;

public class StoredCallImpl implements StoredCall {
	
	private String procedureName;
	private boolean function;
	private SqlParameter [] parameters;
	private List<CursorParameter<?>> cursorParameters;
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall call;
	
	public StoredCallImpl() {
		parameters = new SqlParameter[0];
		cursorParameters = new ArrayList<>();
	}
	
	StoredCallImpl(SimpleJdbcCall call) {
		this.call = call;
	}
	
	@PostConstruct
	public void postConstruct() {
		SimpleJdbcCall theCall = new SimpleJdbcCall(jdbcTemplate);
		
		if (function) {
			theCall = theCall.withFunctionName(procedureName);
		} else {
			theCall = theCall.withProcedureName(procedureName);
		}
		
		theCall = theCall.declareParameters(parameters);
		
		for (CursorParameter<?> cursorParameter : cursorParameters) {
			theCall = theCall.returningResultSet(cursorParameter.getParameterName(), cursorParameter.getRowMapper());
		}
		this.call = theCall;
	}
	
	@Override
	public StoredCallResult execute(Object... args) {		
		Object[] argsToUse = new Object[args.length];
		
		for (int i = 0; i < argsToUse.length; i++) {
			Object arg = args[i];
			Object argToUse = processArg(arg);
			argsToUse[i] = argToUse;
		}
		
		Map<String, Object> resultMap = call.execute(argsToUse);
		return new StoredCallResult(resultMap);
	}
	
	private Object processArg(Object arg) {
		if (arg == null) {
			return null;
		}
		
		if (arg instanceof DateTime) {
			DateTime joda = (DateTime) arg;
			return new TimestampAsDateTimeSqlValue(joda);
		}
		
		return arg;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public boolean isFunction() {
		return function;
	}

	public void setFunction(boolean function) {
		this.function = function;
	}
	
	public SqlParameter[] getParameters() {
		return parameters;
	}

	public void setParameters(SqlParameter[] parameters) {
		this.parameters = parameters;
	}

	public List<CursorParameter<?>> getCursorParameters() {
		return cursorParameters;
	}

	public void setCursorParameters(List<CursorParameter<?>> cursorParameters) {
		this.cursorParameters = cursorParameters;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
