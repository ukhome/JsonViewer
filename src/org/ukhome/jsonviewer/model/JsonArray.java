package org.ukhome.jsonviewer.model;

import org.ukhome.jsonviewer.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

public class JsonArray extends Json {

    private JSONArray array = new JSONArray();

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
    }

    @Override
    public String toString() {
        return getJsonString(array);
    }

}
