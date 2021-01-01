import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonValidation {

	@Test
	public void Coursevalidation() {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(Payload.GetResponse());
		
		//Get website,PurchaseAmt from dashboard
		String website=js.getString("dashboard.website");
		System.out.println("Website : "+website);
		int PurchageAMT=js.getInt("dashboard.purchaseAmount");
		System.out.println("PurchageAMT : "+PurchageAMT);
		
		
		// Get number of courses
		 int courseCount=js.getInt("courses.size()");
		 System.out.println("Total courses : "+courseCount);
		 
		 // Get title for all courses
		 String FirstCourseTitle=js.getString("courses[0,1,2].title");
		 System.out.println("First Couse Title: "+FirstCourseTitle);
		 
		 // Print all course titles and their respective prices
		 
		 for (int i=0;i<courseCount;i++)
		 {
			 
			 String CourseTitle=js.getString("courses["+i+"].title"); // To add variable between string we use "courses["+i+"]"
			 int CourseAmt=js.getInt("courses["+i+"].price");
			 System.out.println("Title/Amount for Course"+i+" is:  "+CourseTitle+"/"+CourseAmt);
			
							 
		 }
		 
		 
		 // Print total copies of courses sold and match the addition with total sales
		 int Totalsale = 0;
		 for(int i=0;i<courseCount;i++)
		 {	 
		   int price=js.getInt("courses["+i+"].price");
		   int copies=js.getInt("courses["+i+"].copies");
		   int totalcopies=price*copies;
		   Totalsale=totalcopies+Totalsale;
		   
		 } 
		   if (PurchageAMT!=Totalsale)
		      System.out.println("Sales does not match");
		   else 
			   System.out.println("Sales match");
		  //  Assert.assertEquals(PurchageAMT, Totalsale);
		   
		 // Print all copies sold by RPA
		 
			 for(int i=0;i<courseCount;i++)
			 {	 
			   String title=js.get("courses["+i+"].title");
			   if ("RPA".equalsIgnoreCase(title))
			   {
				   int copies=js.getInt("courses["+i+"].copies");
				   System.out.println("copies for RPA:"+ copies);
				   break;
			   }   
			   
			   
			 } 
		   
		 
		 
	}

}
