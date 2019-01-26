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
		jsonObject = JSONObject.parseObject(jsonStr);
	}

	@Override
	public String[] getChildren() {
		List<String> children = new ArrayList<String>();
		Set<String> keySet = jsonObject.keySet();
		for (String key : keySet) {
			
			String value = jsonObject.getString(key);
			
			try {
				Json.getInstance(value);
//				children.add("{\"" + key  + "\":" + value + "}");
				children.add(value);
			} catch (Exception e) {
				children.add(key + ':' + value);
			}
		}
		return children.toArray(new String[children.size()]);
	}
	
	@Override
	public boolean hasChildern() {
		return !jsonObject.isEmpty();
	}
}
