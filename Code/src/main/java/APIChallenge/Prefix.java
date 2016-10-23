package APIChallenge;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * Retrieve a prefix and array. Create an array of the elements that do not start with the 
 * prefix and send it to an endpoint in JSON format.
 */
public class Prefix {

	public static void main(String[] args) {
		Map<String , String> map = CommonMethods.makeJSON(false);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		Map<String , Integer> responseMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/prefix", json);
		System.out.println(responseMap);
		
		StringBuilder response = CommonMethods.getResponsefromURL();
		ArrayList<String> parseArray = CommonMethods.parseResponse(response, "[a-zA-Z]+");
		
		ArrayList<String> answerArray = new ArrayList<String>();
		String prefix = parseArray.get(1);
		int length = prefix.length();
		int arrayStartIndex = 3;
		for(int index = arrayStartIndex; index < parseArray.size(); index++) {
			String test = parseArray.get(index).substring(0 , length);
			if (!(test.equals(prefix))) {
				answerArray.add(parseArray.get(index));
			}
		}
		JsonArray jArray = new JsonArray();
		for (String s : answerArray) {
			jArray.add(s);
		}
		JsonObject finalJSON = new JsonObject();
		finalJSON.addProperty("token", "bd65002e3921154babf10a8219856548");
		finalJSON.add("array", jArray);
		System.out.println(finalJSON.toString());
		
		Map<String, Integer> validateMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/prefix/validate", finalJSON.toString());
		System.out.print(validateMap);
	}
	

}
