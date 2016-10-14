package Endpoint;

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
		connectToURL("http://challenge.code2040.org/api/register");
		Map<String , String> map = new HashMap<String , String>();
		map.put("token", token);
		map.put("github", github);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		writeToURL(json);
	}
	
	/**
	 * Opens a connection to the requested URL and determines HTTP method "POST". 
	 * @param urlName String that represents URL.
	 */
	public static void connectToURL(String urlName) {
		try {
			URL url = new URL(urlName);
			urlConnect = (HttpURLConnection) url.openConnection();
			System.out.println("Connection to url, " + url.toString() + " is opened.");
			urlConnect.setDoOutput(true);
			urlConnect.setRequestMethod("POST");
			urlConnect.setRequestProperty("Content-Type", "application/json");
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends data to URL through POST method
	 * @param message String that represent data to be sent to URL.
	 */
	public static void writeToURL(String message) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(urlConnect.getOutputStream());
			writer.write(message);
			writer.close();

			int responseCode = urlConnect.getResponseCode();
			System.out.println(responseCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
