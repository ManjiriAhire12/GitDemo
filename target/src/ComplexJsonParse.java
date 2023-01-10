import Files.payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
	
		JsonPath js=new JsonPath(payloads.CoursePrice());
		
		//1.Print no of courses returned by API
		
		int count=  js.getInt("courses.size()"); //.size method is used only for Arrays
		System.out.println(count);

		
		//2.Print purches amount
		int totalamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);
		
		//3.Print title of first course
		String titleOfFirstCourse=js.getString("courses[0].title");
		System.out.println(titleOfFirstCourse);

	 //4.print all course titles and their respective prices
	for (int i=0;i<count;i++)
	{
		String titles=js.get("courses["+i+"].title");
		System.out.println( js.get("courses["+i+"].price").toString());
		System.out.println(titles);
	}
	
	
	//5.print no of copies sold by RPA
	for(int i=0;i<count;i++) {
		String title=js.get("courses["+i+"].title");
		if(title.equalsIgnoreCase("RPA"))
		{
			//copies sold
			int copies=js.get("courses["+i+"].copies");
			System.out.println("Copies of RPA is :"+ copies);
		
		}
	}
	
	
	}

}
