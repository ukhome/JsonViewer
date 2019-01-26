package org.ukhome.jsonviewer.model;

import org.ukhome.jsonviewer.util.StringUtils;

public class Json implements IBase<String>{
	
	protected String jsonStr;
	
	protected Json(String jsonStr) 
	{
		this.jsonStr = jsonStr;
	}

	public static Json getInstance(String jsonStr)
	{
		Json instance = null;
		if(StringUtils.isEmpty(jsonStr)) return instance;
		
		char firstChar = jsonStr.charAt(0);
		switch (firstChar) {
			case '[':
				instance = new JsonArray(jsonStr);
				break;
			case '{' :
				instance = new JsonObject(jsonStr);
				break;
			default:
				instance = jsonStr.contains(":") ? new JsonItem(jsonStr) : new JsonItem(jsonStr + ": ");
				break;
		}
		return instance;
	}
	
	@Override
	public String toString() {
		return jsonStr;
	}
	
	@Override
	public boolean hasChildern() {
		return false;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getParent() {
		return null;
	}

	@Override
	public void setParent(String object) {
		
	}

	@Override
	public void setChildren(String[] children) {
		
	}

	@Override
	public String[] getChildren() {
		return null;
	}

	@Override
	public int currentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

}
