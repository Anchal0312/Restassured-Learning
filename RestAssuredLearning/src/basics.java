import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Files.Payload;


public class basics {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	//  Add Basic URI	
	//	given(): To mention all the inputs like QueryParameters,Header,Body
	//  when():	Submit the API using CRUD methods like PUT/POST/GET/DELETE/PATCH, Resource&HTTP method come under this
	//  then(): To Assert for validating the response like Status Code,Actual Response	
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"C:/Users/HP/eclipse-workspace/RestAssuredLearning/src/TestSuites/url.txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				RestAssured.baseURI=line;
				// Given() gives all the inputs for test
				String response=given().log().all().queryParam("key","qaclick123")
				.headers("Content-Type","application/json").body(Payload.Addplace()) //Added reference to another class which contain payload
				.when().post("maps/api/place/add/json")           // POST request along with resource path
				.then().assertThat().statusCode(200)  //Add Assertions to validate response of AddPlace
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();
				 
				System.out.println(response);
				
				JsonPath js=new JsonPath(response); //For Parsing JSON as we want or hold PlceID for next test
				String placeId=js.getString("place_id");
				
				System.out.println(placeId);
				line = reader.readLine();
				Map<Integer, String> m = new HashMap<Integer, String>();
				m.put(new Integer(2), "Anchal");
				m.get(2);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}       // Set Base URL
		
			
	}

}
