package org.ukhome.jsonviewer.model;

import org.ukhome.jsonviewer.util.StringUtils;

public class JsonItem extends Json{
	
	private String item;
	
	protected JsonItem(String name, String item) {
		super(name);
		this.name = name;
		this.item = item;
	}
	
	@Override
	public String toString() {
		return this.name + "=" + this.item;
	}

	@Override
	public Json[] getChildren() {
		return new Json[] {Json.getInstance(item)};
	}
	
	@Override
	public boolean hasChildern() {
		return StringUtils.isJson(this.item);
	}

	
}
