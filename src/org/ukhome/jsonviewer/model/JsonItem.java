package org.ukhome.jsonviewer.model;

public class JsonItem extends Json{
	
	private String key;
	private String value;
	
	protected JsonItem(String jsonStr) {
		super(jsonStr);
		this.key = jsonStr.substring(0, jsonStr.indexOf(':'));
		this.value = jsonStr.substring(jsonStr.indexOf(':') + 1);
	}
	
	@Override
	public String getName() {
		return this.key;
	}

	public String getValue() {
		return value;
	}
	
}
