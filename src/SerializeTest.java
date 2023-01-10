import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;


public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setWebsite("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setName("Frontline house");
		List<String> myList=new ArrayList<String> ();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res=given().log().all().queryParam("key", "qaclick123").body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);

	}

}
