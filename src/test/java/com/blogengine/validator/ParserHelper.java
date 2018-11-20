package com.blogengine.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserHelper {

	@SuppressWarnings("rawtypes")
	public static Collection data() {
		
		ArrayList<Object[]> resultData = new ArrayList<>();
		
		ClassLoader cl = ParserHelper.class.getClassLoader();
	
		String resultString = "";	
		// JSON to String
		try {
			resultString = IOUtils.toString(cl.getResourceAsStream("testEmails.json"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		JSONParser parser = new JSONParser();	
		JSONObject rawJSON = null;
		
		// JSON (from String) to Object
		try {
			rawJSON = (JSONObject) parser.parse(resultString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	
		for(Object key : rawJSON.keySet().toArray()) {
			JSONObject json = (JSONObject) rawJSON.get(key);
			resultData.add(new Object[] {json});					
		}
	
		return resultData;			
	}
	
}