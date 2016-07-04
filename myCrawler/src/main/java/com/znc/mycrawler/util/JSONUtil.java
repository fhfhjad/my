package com.znc.mycrawler.util;

import com.google.gson.Gson;

public class JSONUtil {
	
	private static Gson gson = new Gson();
	
	public static String  ObjectToJSON(Object obj){
		return gson.toJson(obj);
	}
	
	public static Object JSONToObject(String json,Class cl){
		return gson.fromJson(json, cl);
	}
}
