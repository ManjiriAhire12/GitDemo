import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReusableMethods;
import Files.payloads;
import POJO.GetPlace;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Validate if addplace API is working as expected or not
		
		//1.Given----all input details
		//2.When-----submit the API
		//3.Then----validate the response
		
		//set baseuri
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payloads.AddPlace()).when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope", equalTo ("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String PlaceId=js.getString("place_id");
		System.out.println(PlaceId);
		
		
		//Update Place
		String newAddress =" Summer  walk, Africa";
		
	ValidatableResponse vr= given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceId+"\",\r\n"
				+ "\"address\":\" "+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	
	
//		//Get place
//		String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceId)
//		.when().get("maps/api/place/get/json")
//		.then().assertThat().log().all().statusCode(200).extract().response().asString();
//		
//		JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
//		String actAddress=js1.getString("address");
//		System.out.println(actAddress);
//		Assert.assertEquals(actAddress, newAddress);
//	
//	}
	
	
	
	

}
}