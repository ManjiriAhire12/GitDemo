import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommerceAPITest {

	public static void main(String[] args) {
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		 .setContentType(ContentType.JSON).build();
		
		
		LoginRequest l=new LoginRequest();
		l.setUserEmail("manjiriahire@gmail.com");
		l.setUserPassword("Manjiri@123");
		
		RequestSpecification reqLogin=given().log().all().spec(req).body(l);
		LoginResponse loginres=reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		System.out.println("\ntoken:"+loginres.getToken());
		String token=loginres.getToken();
		System.out.println("\nuserId:"+loginres.getUserId());
		System.out.println("\nmessage:"+loginres.getMessage());
		String userId=loginres.getMessage();
		
		//Add product/create product
		RequestSpecification addProductReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).build();
		
		RequestSpecification reqAddProduct=given().log().all().spec(addProductReq).param("productName", "laptop").param("productAddedBy", userId)
		.param("productCategory", "fashion").param("productSubCategory", "shirts").
		param("productPrice","11500").param("productDescription", "Addias Originals")
		.param("productFor", "women").multiPart("productImage",new File("C:\\Image_CreateProduct\\images.png"));
		
		String addProductResponse=reqAddProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		JsonPath js = new JsonPath (addProductResponse);
		String productId=js.get("productId");
		
		
		//Create Order
		RequestSpecification  createOrderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON).build();
		OrderDetails orderdetails=new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productId);
		
		List<OrderDetails> orderDetailsList=new ArrayList<OrderDetails> ();
		orderDetailsList.add(orderdetails);
		
		
		Orders orders=new Orders();
		orders.setOrders(orderDetailsList);
				
		RequestSpecification createOrderReq1=given().log().all().spec(createOrderReq).body(orders);
		String responseAddOrder=createOrderReq1.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
		
		
		

		

	}

}
