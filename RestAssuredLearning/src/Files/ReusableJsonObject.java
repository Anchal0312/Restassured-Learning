package Files;

import io.restassured.path.json.JsonPath;

public class ReusableJsonObject {
	
	public static JsonPath JsonObj(String response)
	{
		
		
		JsonPath js=new JsonPath(response);
		return js;
		
	}

}
