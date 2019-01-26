package org.ukhome.jsonviewer.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class JsonArray extends Json{
	
	private List<String> jsonArray = new ArrayList<String>();

	protected JsonArray(String jsonStr) {
		super(jsonStr);
		jsonArray = JSONArray.parseArray(jsonStr, String.class);
	}

	@Override
	public String[] getChildren() {
		return jsonArray.toArray(new String[jsonArray.size()]);
	}
	
	@Override
	public boolean hasChildern() {
		return !jsonArray.isEmpty();
	}

}
