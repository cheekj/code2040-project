package APIChallenge;



import java.util.ArrayList;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.google.gson.Gson;


public class Date {
	
	final static String token = "bd65002e3921154babf10a8219856548";

	public static void main(String[] args) {
		Map<String , String> map = CommonMethods.makeJSON(false);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		Map<String , Integer> responseMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/dating", json);
		System.out.println(responseMap);
		
		StringBuilder response = CommonMethods.getResponsefromURL();
		ArrayList<String> responseArray = CommonMethods.parseResponse(response , "[0-9a-zA-Z]+");
		Integer year = Integer.valueOf(responseArray.get(1));
		Integer month = Integer.valueOf(responseArray.get(2));
		Integer day = Integer.valueOf(responseArray.get(3).substring(0, 2));
		Integer hour = Integer.valueOf(responseArray.get(3).substring(3, 5));
		Integer minute = Integer.valueOf(responseArray.get(4));
		Integer second = Integer.valueOf(responseArray.get(5).substring(0, 2));
		DateTime date = new DateTime(year , month , day, hour , minute , second, DateTimeZone.getDefault());
		Integer interval = Integer.valueOf(responseArray.get(7));
		
		DateTime newDate = date.plusSeconds(interval);
		ArrayList<String> answerArray = CommonMethods.parseResponse(new StringBuilder(newDate.toString()),"[0-9a-zA-Z]+");
		//Don't need last two.
		answerArray.remove(answerArray.size() - 1);
		answerArray.remove(answerArray.size() - 1);
		
		// Used Joda Time library to add seconds to time.
		String finalDate = "";
		int hourIndex = 2;
		int secondIndex = answerArray.size() - 1;
		for (int index = 0; index < answerArray.size(); index++) {
			if (index < hourIndex) {
				finalDate += answerArray.get(index) + "-";
			} else if (index >= hourIndex && index != secondIndex) {
				finalDate+= answerArray.get(index) + ":";
			} else {
				finalDate += answerArray.get(index) + "Z";
			}
		}
		map.put("datestamp", finalDate);
		String finalJSON = gson.toJson(map);
		Map<String , Integer> validateMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/dating/validate", finalJSON);
		System.out.println(validateMap);
		
	} 
	
}