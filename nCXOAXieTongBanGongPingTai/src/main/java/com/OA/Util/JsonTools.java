package com.OA.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




public class JsonTools {
	public static <T> List<T> getListMapData(String jsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();

			list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
			}.getType());
		} catch (Exception e) {
		}
		return list;
	}

	public static List<Map<String, Object>> getUser(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// Map<String, Object> map=(Map<String, Object>)
			// JSONObject.toBean(JSONObject.fromObject(jsonString),HashMap.class);
			Gson gson = new Gson();
			list = gson.fromJson(jsonString,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());
		} catch (Exception e) {
		}
		return list;
	}

	public static Map<String, Object> getMapData(String jsonString) {
		Map<String, Object> list = new HashMap<String, Object>();
		try {
			// Map<String, Object> map=(Map<String, Object>)
			// JSONObject.toBean(JSONObject.fromObject(jsonString),HashMap.class);
			Gson gson = new Gson();
			list = gson.fromJson(jsonString,
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * @param object
	 *            是对解析的集合的类型
	 * @return
	 */
	public static String createJsonString(Object value) {
		Gson gson = new Gson();
		String str = gson.toJson(value);
		return str;
	}
	
	/**
	 * 获取String类型的值
	 * 
	 * @param obj
	 * @param name
	 * @return
	 */
	public static String getString(JSONObject obj, String name) {
		if (obj.isNull(name)) {
			return null;
		} else {
			return obj.optString(name);
		}
	}

	/**
	 * 获取Int类型的值
	 * 
	 * @param obj
	 * @param name
	 * @return
	 */
	public static int getInt(JSONObject obj, String name) {
		if (obj.isNull(name)) {
			return 0;
		} else {
			return obj.optInt(name);
		}
	}

	/**
	 * 获取double类型值
	 * 
	 * @param obj
	 * @param name
	 * @return
	 */
	public static double getDouble(JSONObject obj, String name) {
		if (obj.isNull(name)) {
			return 0.0;
		} else {
			return obj.optDouble(name);
		}
	}

	/**
	 * 获取boolean类型值
	 * 
	 * @param obj
	 * @param name
	 * @return
	 */
	public static boolean getBoolean(JSONObject obj, String name) {
		if (obj.isNull(name)) {
			return false;
		} else {
			return obj.optBoolean(name);
		}
	}
}
