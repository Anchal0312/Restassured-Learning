/*Test to pass static valuesand calling payload.addBook */
package Book;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableJsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class addBookFixVariableCall {
	
   @Test
	public void AddNewBook()
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json").
		body(Payload.addbook("ihk","234")).      //Calling payload,addbook with these static values rather than hardcode in paylaod
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();
		
		//System.out.println(response);
		JsonPath js1= ReusableJsonObject.JsonObj(response);
		 String ID= js1.getString("ID");
		System.out.println(ID);
		
				
	}

}
