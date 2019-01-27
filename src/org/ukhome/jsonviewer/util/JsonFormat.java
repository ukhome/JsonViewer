package org.ukhome.jsonviewer.util;

import org.ukhome.jsonviewer.model.JsonArray;
import org.ukhome.jsonviewer.model.JsonObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonFormat {
	
	public static final String JSON = "{\"time\":\"2017-10-15 18:09:56\",\"userName\":\"\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"status\":null}";
	public static final String JSON0 = "[{\"time\":\"2017-10-15 18:09:56\",\"userName\":\"\",\"status\":null}]";
	public static final String JSON1 = "{\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userName\":\"\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"status\":null}}";
	public static final String JSON2 = "[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":null}}]";
	public static final String JSON3 = "{\"11\":[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":1}}]}";
	public static final String JSON4 = "[[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":null}}]]";


	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr)) return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		boolean isInQuotationMarks = false;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '"':
				if (last != '\\') {
					isInQuotationMarks = !isInQuotationMarks;
				}
				sb.append(current);
				break;
			case '{':
			case '[':
				sb.append(current);
				if (!isInQuotationMarks) {
					sb.append('\n');
					indent++;
					addIndentBlank(sb, indent);
				}
				break;
			case '}':
			case ']':
				if (!isInQuotationMarks) {
					sb.append('\n');
					indent--;
					addIndentBlank(sb, indent);
				}
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\' && !isInQuotationMarks) {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}
		
		return sb.toString();
	}

	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append("    ");
		}
	}
	
	public static JSON parseJson(String str) {
		JSON json;
		try {
			json = JSONArray.parseArray(str);
		} catch (JSONException e) {
			json = JSONObject.parseObject(str);
		}
		return json;
	}
	
	public static Object[] toArray(Object obj) {
		Object[] array = new Object[0];
		if (obj instanceof JsonArray) {
			array = ((JSONArray)obj).toArray(array);
		} else if(obj instanceof JsonObject) {
			array = new JsonObject[]{(JsonObject)obj};
		}
		return array;
	}

	public static void main(String[] args) {
		String str = "{\"code\":10000,\"msg\":null,\"data\":{\"id\":\"7aa0eb56-1026-4497-a42e-4c39f5e3dcf1\",\"topicId\":\"0876ab84-a478-417b-91bc-849843c191a5\",\"title\":null,\"commentId\":null,\"content\":\"开发者平台自动化测试：针对帖子发表评论\",\"images\":\"\",\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"commentNum\":0,\"status\":0}}";
		String result = formatJson(str);
		System.out.println(result);
		
	}

}
