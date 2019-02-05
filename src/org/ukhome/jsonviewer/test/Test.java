package org.ukhome.jsonviewer.test;

import java.util.Arrays;
import java.util.List;

import org.ukhome.jsonviewer.model.Json;
import org.ukhome.jsonviewer.util.JsonFormat;

public class Test {

    public static void main(String[] args) {

        pt(JsonFormat.JSON);
//		pt(JsonFormat.JSON1);
//		pt(JsonFormat.JSON3);
    }

    private static void pt(String json) {
        Json jsonObject = Json.getInstance(json);

        if (jsonObject.hasChildern()) {
            List<Json> list = Arrays.asList(jsonObject.getChildren());
            System.out.println(list.size() + " : " + list);
        }
        System.out.println("-----------------------------------------");
    }

}
