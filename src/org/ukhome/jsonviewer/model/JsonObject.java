package org.ukhome.jsonviewer.model;

import java.util.Set;

import org.ukhome.jsonviewer.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class JsonObject extends Json {

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
            Object obj = jsonObject.get(key);
            Json child = Json.getInstance(getJsonString(obj), key);
            if (null == obj || StringUtils.isJson(obj.toString()))
                child.setName(key);//JsonItem对象的item是,或者是json类型字符串
            this.children.add(child);
        }
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
        return getJsonString(jsonObject);
    }
}
