package org.ukhome.jsonviewer.util;

import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
        String str = "{'age':'23','aihao':['pashan','movies'],'name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies','name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies']}]}}";
        System.out.println(new JSONObject().isEmpty());
	}
	
	
}
