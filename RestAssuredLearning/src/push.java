
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;


public class push {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	//  Add Basic URI	
	//	given(): To mention all the inputs like QueryParameters,Header,Body
	//  when():	Submit the API using CRUD methods like PUT/POST/GET/DELETE/PATCH, Resource&HTTP method come under this
	//  then(): To Assert for validating the response like Status,Code,Actual Response	
	            
		        String key="qaclick123";
		
				RestAssured.baseURI= "https://rahulshettyacademy.com";
				// Given() gives all the inputs for test
				String response=given().log().all().queryParam(key)
				.headers("Content-Type","application/json").body(Payload.Addplace()) //Added reference to another class which contain payload
				.when().post("maps/api/place/add/json")           // POST request along with resource path
				.then().assertThat().statusCode(200)  //Add Assertions to validate response of AddPlace
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();
				 
				//System.out.println(response);
				
				JsonPath js=new JsonPath(response); //For Parsing JSON as we want or hold PlceID for next test
				String placeId=js.getString("place_id");
				
				System.out.println("PlaceId of the location posted: "+placeId);
				
				
				//UpdatePlace
				String addressUpdate="70 winter walk, USA";
				
				String updateResponse=given().log().all().queryParam("place_id",placeId).queryParam("key",key).
				body("{\r\n"
						+ "\"place_id\":\""+placeId+"\",\r\n"
						+ "\"address\":\""+addressUpdate+"\",\r\n"
						+ "\"key\":\""+key+"\"\r\n"
						+ "}\r\n"
						+ "").
				when().put("maps/api/place/update/json").
				then().assertThat().statusCode(200).and().
				//.body("msg",equalTo("Address successfully updated"));
				extract().response().asString();
				
				JsonPath js1=new JsonPath(updateResponse);
				String msgupdate=js1.getString("msg");
				System.out.println("Address update response after PUT: "+msgupdate);
				
				
				//GetPlace 
				
				String GetResponse=given().log().all().queryParam("place_id",placeId).queryParam("key",key).
				when().get("maps/api/place/get/json").
				then().log().all().assertThat().statusCode(200).
				extract().response().asString();
									
				//System.out.println(Response1);
				
				
				  JsonPath js2=new JsonPath(GetResponse); 
				  String getUpdatedAdd=js2.getString("address");
				  System.out.println("Updated Address with Get: "+getUpdatedAdd);
				  
				  //Added TestNG jar's to use below Assert method
				  Assert.assertEquals(addressUpdate,getUpdatedAdd); //To check whether both the address matches via TestNg method Assert
									  
				  
					  
					  
				  
				 
				
			}
			
}


