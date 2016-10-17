package APIChallenge;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;


public class Registration {
	
	final static String token = "bd65002e3921154babf10a8219856548";
	final static String github = "https://github.com/cheekj/code2040-project.git";
	static HttpURLConnection urlConnect;

	public static void main(String[] args) {
		try {
			URL url = new URL("http://challenge.code2040.org/api/register");
			urlConnect = (HttpURLConnection) url.openConnection();
			System.out.println("Connection to url, " + url.toString() + " is opened.");
			urlConnect.setDoOutput(true);
			urlConnect.setRequestMethod("POST");
			urlConnect.setRequestProperty("Content-Type", "application/json");
			
			Map<String , String> map = new HashMap<String , String>();
			map.put("token", token);
			map.put("github", github);
			Gson gson = new Gson();
			String json = gson.toJson(map);
			
			OutputStreamWriter writer = new OutputStreamWriter(urlConnect.getOutputStream());
			writer.write(json);
			writer.close();

			int responseCode = urlConnect.getResponseCode();
			System.out.println(responseCode);
			urlConnect.disconnect();
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
