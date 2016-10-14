package Endpoint;
import com.google.gson.*;

public class JSONDictionary {
	private String token;
	private String githubLink;
	
	public JSONDictionary(String token , String githubLink) {
		this.token = token;
		this.githubLink = githubLink;
	}
	
	public String makeJSON() {
		Gson gson = new Gson();
		JSONDictionary json = new JSONDictionary(token , githubLink);
		String answer = gson.toJson(json);
		return answer;
	}
	
	

}
