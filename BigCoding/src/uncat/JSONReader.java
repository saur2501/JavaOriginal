package uncat;

import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSONReader {

	public static Object get(JSONObject jsonObject, String string) {
		String[] cascadePath = string.split("/");
		int i;														//the cascade index to be read
		String currentReadingStatus= "JSONObject";
		System.out.println(Arrays.toString(cascadePath));
		JSONArray jsonArray = new JSONArray();
		for(i = 0; i < cascadePath.length - 1; i++) {
			System.out.println("Checking cascade entry " + i + " = " + cascadePath[i]);
			if(currentReadingStatus == "JSONObject") {
				String value = (String) jsonObject.get(cascadePath[i]);
				System.out.println("Value at index " + i + " in JSONObject is " + value);
				if(value.charAt(0) == '[') {
					jsonArray = (JSONArray) jsonObject.get(cascadePath[i]);
					currentReadingStatus = "JSONArray";
					System.out.println("Current State for jsonArray = " + jsonArray.toString());
				} else if(value.charAt(0) == '{') {
					jsonObject = (JSONObject) jsonObject.get(cascadePath[i]);
					currentReadingStatus = "JSONObject";
					System.out.println("Current State for jsonObject = " + jsonObject.toString());
				} else {
					value = (String) jsonObject.get(cascadePath[i]);
					currentReadingStatus = "EndReached";
					break;
				}
			} else if(currentReadingStatus == "JSONArray") {
				String value = (String) jsonArray.get(Integer.parseInt(cascadePath[i]));
				System.out.println("Value at index " + i + " in JSONArray is " + value);
				if(value.charAt(0) == '[') {
					jsonArray = (JSONArray) jsonArray.get(Integer.parseInt(cascadePath[i]));
					currentReadingStatus = "JSONArray";
					System.out.println("Current State for jsonObject = " + jsonArray.toString());
				} else if(value.charAt(0) == '{') {
					jsonObject = (JSONObject) jsonArray.get(Integer.parseInt(cascadePath[i]));
					currentReadingStatus = "JSONObject";
					System.out.println("Current State for jsonArray = " + jsonObject.toString());
				} else {
					value = (String) jsonArray.get(Integer.parseInt(cascadePath[i]));
					currentReadingStatus = "EndReached";
					break;
				}
			}
		}
		Object value = null;
		if(currentReadingStatus == "EndReached") 
			System.out.println("Planning to read '" + cascadePath[i+1] + "' while abrupt end was encountered!");
		else if (currentReadingStatus == "JSONObject") {
			value = jsonObject.get(cascadePath[i]);
			System.out.println("Final Value = " + value);
		}
		else if (currentReadingStatus == "JSONArray") {
			value = jsonArray.get(Integer.parseInt(cascadePath[i]));
			System.out.println("Final Value = " + value);
		}
		return value;

	}
	
}
