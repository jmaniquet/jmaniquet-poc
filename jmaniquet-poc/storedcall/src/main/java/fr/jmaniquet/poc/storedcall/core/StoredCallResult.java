package fr.jmaniquet.poc.storedcall.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

public class StoredCallResult {

	private static final String FUNCTION_RESULT_KEY = "FUNCTION_RESULT";
	private Map<String, Object> resultMap;

	public StoredCallResult(Map<String, Object> resultMap) {
		super();
		this.resultMap = resultMap;
	}
	
	public DateTime getDateTime(String key) {
		return extractFromMap(key);
	}
	
	public Integer getInteger(String key) {
		return extractFromMap(key);
	}
	
	public Integer getIntegerFunctionResult() {
		return extractFromMap(FUNCTION_RESULT_KEY);
	}
	
	public <T> List<T> getList(String key) {
		return extractFromMap(key);
	}
	
	public Long getLong(String key) {
		return extractFromMap(key);
	}
	
	public Object getResultProperty(String key) {
		return this.resultMap.get(key);
	}
	
	public String getString(String key) {
		return extractFromMap(key);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T extractFromMap(String key) {
		return (T) this.resultMap.get(key);
	}
	
	/**
	 * Mainly for debugging purposes
	 * @return
	 */
	public String resultMapAsString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();
		for (Map.Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			sb.append(key)
				.append(" = ")
				.append(value);
			if (value != null) {
				sb.append(" (")
				.append(value.getClass())
				.append(")");
			}
			sb.append("/n");	
		}
		sb.append("]");
		return sb.toString();
	}
}
