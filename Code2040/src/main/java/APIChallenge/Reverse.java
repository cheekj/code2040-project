package APIChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Reverse {
	
	final static String token = "bd65002e3921154babf10a8219856548";

	public static void main(String[] args) {
		try {
			URL url = new URL("http://challenge.code2040.org/api/reverse");
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
			StringBuilder answer = response.reverse();
			map.put("string", answer.toString());
			String reverse = gson.toJson(map);
			System.out.println(reverse);
			
			URL urlValidate = new URL("http://challenge.code2040.org/api/reverse/validate");
			HttpURLConnection connectValidate = (HttpURLConnection) urlValidate.openConnection();
			connectValidate.setDoOutput(true);
			connectValidate.setRequestMethod("POST");
			connectValidate.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter writerValidate = new OutputStreamWriter(connectValidate.getOutputStream());
			writerValidate.write(reverse);
			writerValidate.flush();
			writerValidate.close();
			int code = connectValidate.getResponseCode();
			System.out.println(code);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
