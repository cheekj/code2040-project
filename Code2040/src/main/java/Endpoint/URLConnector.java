package Endpoint;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class URLConnector   {
	private URL url;
	HttpURLConnection urlConnect;
	
	public URLConnector() {
		try {
			url = new URL("http://challenge.code2040.org/api/register");
			urlConnect = (HttpURLConnection) url.openConnection();
			urlConnect.setDoOutput(true);
			urlConnect.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToURL(String message) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(urlConnect.getOutputStream());
			writer.write(message);
			writer.close();
			if (urlConnect.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("Good job Jonathan");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
