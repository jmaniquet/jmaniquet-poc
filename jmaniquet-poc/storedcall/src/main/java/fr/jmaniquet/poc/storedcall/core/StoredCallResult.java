package fr.jmaniquet.poc.storedcall.core;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

public class StoredCallResult {

	private Map<String, Object> resultMap;

	StoredCallResult(Map<String, Object> resultMap) {
		super();
		this.resultMap = resultMap;
	}
	
	public DateTime getDateTime(String key) {
		return extractFromMap(key);
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
}
