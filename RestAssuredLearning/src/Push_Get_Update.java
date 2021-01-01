import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Files.Place;
import Files.location;
import Files.types;

public class Push_Get_Update {

	static String baseURI= "https://rahulshettyacademy.com";	
	static String placeId;
	
	public static void testPush() {
		Place want = new Place();
		want.accuracy = 50;
		want.address = "Frontline house";
		want.location = new location();
		want.location.lat = "-38.383494";
		want.location.lng = "33.427362";
		want.phone_number = "(+91) 983 893 3937";
		want.types = new types();
		want.types.shoepark = "";
		want.types.shop = "";
		want.address = "29, side layout, cohen 09";
		want.name = "Frontline house";
		want.website = "http://rahulsh.com";
		want.language = "French-IN";
		RestAssured.baseURI = baseURI;
		// Given() gives all the inputs for test
		String response=given().queryParam("key","qaclick123")
		.headers("Content-Type","application/json").body(want.toString()) //Added reference to another class which contain payload
		.when().post("maps/api/place/add/json")           // POST request along with resource path
		.then().assertThat().statusCode(200)  //Add Assertions to validate response of AddPlace
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		//System.out.println(response);
		JsonPath js=new JsonPath(response); //For Parsing JSON as we want or hold PlceID for next test
		System.out.println("PlaceId from Push Method Display as Response");
		placeId=js.getString("place_id");
		Place got=given().queryParam("key","qaclick123").queryParam(placeId)
				.when().get("maps/api/place/get/json")           
		.then().extract().body().as(Place.class);
		if (want != got) {
		
		}
		System.out.println(placeId);
	}
	public static void testGet() {
		
		RestAssured.baseURI = baseURI;
		
		Place got=given().queryParam("key","qaclick123").queryParam(placeId)
				.when().get("maps/api/place/get/json")           
		.then().extract().body().as(Place.class);
		//.statusCode(200) 
		//.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		//.extract().response().asString();
		//System.out.println(response);
		//JsonPath js=new JsonPath(response); //For Parsing JSON as we want or hold PlceID for next test
		//String placeId=js.getString("place_id");
		
		//System.out.println(placeId);
	}
	public static void testCreate() {
		RestAssured.baseURI = baseURI;
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
		
	}
	public static void testUpdate() {
		RestAssured.baseURI = baseURI;
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
	}
	public static void main(String[] args) {		
		testPush();
		testGet();
	}
}


