
// Test to pass dynamic payload via multidimensional array using DataProvider

package Book;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableJsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class AddBook {
	
	@Test(dataProvider="BookData")
	
	public void AddNewBook(String isbn,String aisle)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json").
		body(Payload.addbook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().asString();
		
		//System.out.println(response);
		JsonPath js1= ReusableJsonObject.JsonObj(response);
		 String ID= js1.getString("ID");
		System.out.println(ID);
		
				
	}
	
	
	@DataProvider(name="BookData")
	
	//array=collection of elements
	//multidimensional array= collection of arrays
	 public Object[][] getBookData()
		{
		  return new Object[][]
		  {
	        {"lkm","234"},
	        {"hfg","345"},
	        {"rft3t","246"}
			
		  };
	    }
		
    

}
