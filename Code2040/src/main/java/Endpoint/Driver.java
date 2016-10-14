package Endpoint;
import javax.json.*;

public class Driver {
	
	public static void main(String[] args) {
		URLConnector connector = new URLConnector();
		JSONDictionary json = new JSONDictionary("bd65002e3921154babf10a8219856548" , "https://github.com/maroonAndRed/code2040-project");
		String answer = json.makeJSON();
		connector.writeToURL(answer);
	}

}
