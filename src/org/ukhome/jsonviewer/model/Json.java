package org.ukhome.jsonviewer.model;

import java.util.ArrayList;
import java.util.List;

import org.ukhome.jsonviewer.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Json implements IBase<Json> {

    protected String name;
    protected static SerializerFeature[] features = { SerializerFeature.WriteMapNullValue };
    protected List<Json> children = new ArrayList<Json>();

    protected Json(String jsonStr) {
    }

    protected void makeChildren(String jsonStr) {

    }

    public static Json getInstance(String jsonStr) {
        return getInstance(jsonStr, null);
    }

    public static Json getInstance(String jsonStr, String itemName) {
        Json instance = null;
        if (StringUtils.isEmpty(jsonStr))
            return instance;

        char firstChar = jsonStr.charAt(0);
        switch (firstChar) {
            case '[':
                instance = new JsonArray(jsonStr);
                break;
            case '{':
                instance = new JsonObject(jsonStr);
                break;
            default:
                instance = new JsonItem(itemName, jsonStr);
                break;
        }
        return instance;
    }
    
    protected String getJsonString(Object obj) {
        return JSON.toJSONString(obj, features);
    }

//	@Override
//	public String toString() {
//		return "";
//	}

    @Override
    public boolean hasChildern() {
        return !children.isEmpty();
    }

    @Override
    public Json[] getChildren() {
        return children.toArray(new Json[children.size()]);
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
    public int currentLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

}
