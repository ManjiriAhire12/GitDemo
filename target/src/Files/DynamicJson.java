package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="booksData")
	public void addBook(String isbn , String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String resp=given().header("Content-Type","application/json").
		body(payloads.addBook(isbn,aisle)).  //parameterization
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
	    JsonPath js=ReusableMethods.rawToJson(resp);
	    String id= js.get("ID");
	    System.out.println(id);

}
	
	@DataProvider(name="booksData")
	public Object[][] getData()
	{
		return new Object[][] {{"mnfbc","2383544"},{"euhgfgfrt","4056"},{"wsedijrf","50432"}};
	}
}
