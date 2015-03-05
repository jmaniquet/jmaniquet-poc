package fr.jmaniquet.poc.storedcall.core;

import java.util.Map;

public class StoredCallResult {

	private Map<String, Object> resultMap;

	public StoredCallResult(Map<String, Object> resultMap) {
		super();
		this.resultMap = resultMap;
	}
	
	public Object getResultProperty(String key) {
		return this.resultMap.get(key);
	}
}
