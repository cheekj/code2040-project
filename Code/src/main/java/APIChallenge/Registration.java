package APIChallenge;
import java.util.Map;
import com.google.gson.Gson;


public class Registration {
	
	final static String token = "bd65002e3921154babf10a8219856548";
	final static String github = "https://github.com/cheekj/code2040-project.git";
	
	public static void main(String[] args) {
		Map<String , String> map = CommonMethods.makeJSON(true);
		Gson Gson = new Gson();
		String json = Gson.toJson(map);
		Map<String , Integer> responseMap = CommonMethods.sendJSONToURL("http://challenge.code2040.org/api/register" , json);
		System.out.println(responseMap);
	} 
}
