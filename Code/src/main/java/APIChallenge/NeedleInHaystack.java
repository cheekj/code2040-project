package APIChallenge;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.Gson;


public class NeedleInHaystack {
	
	final static String token = "bd65002e3921154babf10a8219856548";

	public static void main(String[] args) {
		Map<String , String> map = CommonMethods.makeJSON(false);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		Map<String , Integer> responseMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/haystack", json);
		System.out.println(responseMap);
		
		StringBuilder response = CommonMethods.getResponsefromURL();
		ArrayList<String> haystack = CommonMethods.parseResponse(response , "[a-zA-Z]+");
		String needle = haystack.get(1);
		
		//Search through arraylist to find matching. Subtract three from match because arraylist includes needle elements;
		Integer tempMatch = 0;
		for (int index = 0; index < haystack.size(); index++) {
			if (haystack.get(index).equals(needle)) {
				tempMatch = index - 3;
			}
		}
		String match = tempMatch.toString();
		map.put("needle", match);
		String needleJSON = gson.toJson(map);
		Map<String , Integer> validateMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/haystack/validate", needleJSON);
		System.out.println(validateMap);
	} 
	
	
	
	
		

}
