package org.ukhome.jsonviewer.model;

import java.util.ArrayList;
import java.util.List;

import org.ukhome.jsonviewer.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

public class JsonArray extends Json {

    private List<String> jsonArray = new ArrayList<String>();
    private JSONArray array =  new JSONArray();

    protected JsonArray(String jsonStr) {
        super(jsonStr);
        makeChildren(jsonStr);
    }

    @Override
    protected void makeChildren(String jsonStr) {
        
        array = JSONArray.parseArray(jsonStr);
        for (int i = 0; i < array.size(); i++) {
            String arrayItemStr = getJsonString(array.get(i));
            if (!StringUtils.isJson(arrayItemStr))
                throw new JSONException(arrayItemStr);
            Json json = Json.getInstance(arrayItemStr);
            System.out.println(json);
            json.setName("");
            children.add(json);
        }
        
        
//        jsonArray = JSONArray.parseArray(jsonStr, String.class);
//        for (String child : jsonArray) {
//            if (!StringUtils.isJson(child))
//                throw new JSONException(child);
//            Json json = Json.getInstance(child);
//            json.setName("");
//            children.add(json);
//        }
    }

//    @Override
//    public Json[] getChildren() {
//        return children.toArray(new Json[children.size()]);
//    }
//
//    @Override
//    public boolean hasChildern() {
//        return !children.isEmpty();
//    }
    
    @Override
    public String toString() {
//        return jsonArray.toString();
        return getJsonString(array);
    }

}
