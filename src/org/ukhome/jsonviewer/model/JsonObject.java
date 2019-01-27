package org.ukhome.jsonviewer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonObject extends Json{
	
	private JSONObject jsonObject = new JSONObject();
	
	protected JsonObject(String jsonStr) {
		super(jsonStr);
		makeChildren(jsonStr);
	}
	
	@Override
	protected void makeChildren(String jsonStr) {
		jsonObject = JSONObject.parseObject(jsonStr);
		Set<String> keySet = jsonObject.keySet();
		for (String key : keySet) {
			Object obj = jsonObject.getString(key);
			this.children.add(Json.getInstance(obj.toString(), key));
		}
	}
	

	@Override
	public Json[] getChildren() {
		return children.toArray(new Json[children.size()]);
	}
	
	@Override
	public boolean hasChildern() {
		return !children.isEmpty();
	}
	
	@Override
	public String toString() {
		return jsonObject.toString();
	}
}
