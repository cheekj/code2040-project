package APIChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class NeedleInHaystack {
	
	final static String token = "bd65002e3921154babf10a8219856548";

	public static void main(String[] args) {
		try {
			URL url = new URL("http://challenge.code2040.org/api/haystack");
			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
			urlConnect.setDoOutput(true);
			urlConnect.setRequestProperty("Content-Type", "application/json");
			
			Map<String , String> map = new HashMap<String , String>();
			map.put("token", token);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			OutputStreamWriter writer = new OutputStreamWriter(urlConnect.getOutputStream());
			writer.write(json);
			writer.flush();
			writer.close();
			int responseCode = urlConnect.getResponseCode();
			System.out.println(responseCode);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
			StringBuilder response = new StringBuilder();
			String responseLine;
			while((responseLine = input.readLine()) != null) {
				response.append(responseLine);
			}
			
			ArrayList<String> haystack = findNeedle(response);
			String needle = haystack.get(1);
			Integer tempMatch = 0;
			for (int index = 0; index < haystack.size(); index++) {
				if (haystack.get(index).equals(needle)) {
					tempMatch = index - 3;
				}
			}
			
			String match = tempMatch.toString();
			System.out.println(match);
			
			map.put("needle", match);
			String needleJSON = gson.toJson(map);
			System.out.println(needleJSON);
			
			URL urlValidate = new URL("http://challenge.code2040.org/api/haystack/validate");
			HttpURLConnection connectValidate = (HttpURLConnection) urlValidate.openConnection();
			connectValidate.setDoOutput(true);
			connectValidate.setRequestMethod("POST");
			connectValidate.setRequestProperty("Content-Type","application/json");
			
			
			
			OutputStreamWriter writerValidate = new OutputStreamWriter(connectValidate.getOutputStream());
			writerValidate.write(needleJSON);
			writerValidate.flush();
			writerValidate.close();
			System.out.println(connectValidate.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> findNeedle(StringBuilder response) {
		boolean string = false;
		ArrayList<String> answer = new ArrayList<String>();
		String word = "";
		
		for (int index = 0; index < response.length(); index++) {
			String character = response.substring(index, index + 1);
			if (Pattern.matches("[a-zA-Z]+", character)) {
				string = true;
				word += character;
			} else {
				if (word != "") {
					answer.add(word);
					word = "";
				}
				string = false;
			}
			
		}
		return answer;
	}
	
	
		

}
