import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payloads;
import io.restassured.path.json.JsonPath;

// 6.vrify if sum of all course prices mathes with puches amount

public class SumValidation {
	
	
	@Test
	public void sumOfCourse()
	{
		int sum=0;
		JsonPath js=new JsonPath(payloads.CoursePrice());
		int count=js.get("courses.size()");
		//System.out.println(count);
		for(int i=0;i<count;i++)
		{
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amount=price * copies;
			System.out.println(amount);
			sum=sum+amount;
			
			
		}
		System.out.println(sum);
		int totalAmount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, totalAmount);
	}

}
