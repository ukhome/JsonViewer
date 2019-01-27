package org.ukhome.jsonviewer.model;

import java.util.ArrayList;
import java.util.List;

import org.ukhome.jsonviewer.util.StringUtils;

public class Json implements IBase<Json>{
	
	protected String name;
//	protected String name;
	
	
	protected List<Json> children = new ArrayList<Json>();

	
	protected Json(String jsonStr) 
	{
//		makeChildren(jsonStr);
	}
	
	protected void makeChildren(String jsonStr)
	{
		
	}

	public static Json getInstance(String jsonStr)
	{
		return getInstance(jsonStr, null);
	}
	
	public static Json getInstance(String jsonStr, String itemName)
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
			instance = new JsonItem(itemName, jsonStr);
			break;
		}
		return instance;
	}
	
//	@Override
//	public String toString() {
//		return "";
//	}
	
	@Override
	public boolean hasChildern() {
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Json getParent() {
		return null;
	}

	@Override
	public void setParent(Json object) {
		
	}

	@Override
	public void setChildren(Json[] children) {
		
	}

	@Override
	public Json[] getChildren() {
		return null;
	}

	@Override
	public int currentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

}
