package tests;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Reports.ExtentManager;
import constants.StatusCodeConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(listeners.TestNGListeners.class)
public class TestCases  extends BaseTests {
	
   
    ExtentSparkReporter reporter;
 /**
 * get all records for the provided url test for positive test
 * @author Jagath
 *
 */
    @BeforeTest
    public void setReport() {
   	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/");
    extent = ExtentManager.getReports();
    extent.attachReporter(sparkReporter);
    }

  
     @BeforeMethod
    public void initTest1() {
    	 
    	 extent.createTest("read all records...", "to verify read all records successfully");
    }
    
    @Test(priority=1, testName ="read all records...")
    public void case_RecordsgetAll() {
		System.out.println("case_RecordsgetAll");
		 Response response = given().get(BaseUrl);
	        int actualstatusCode = response.getStatusCode();
	        String actualStatusLine=response.getStatusLine();
	        
	try {    
		        AssertJUnit.assertEquals(actualstatusCode, StatusCodeConstants.Ok);
		        AssertJUnit.assertEquals(actualStatusLine,"HTTP/1.1 200 OK");
		      test.log(Status.INFO, "Starting Test");
		  	  test.log(Status.INFO, "Read all records Test");
		  	  test.log(Status.PASS, "Test passed ;");       
	 } catch (AssertionError e) {
			  test.log(Status.INFO, " Assert Test Error");
			  test.log(Status.INFO, "Read all records Test");
			  test.log(Status.ERROR, "Test Error ;"+e.getMessage());
	 		}      
	 }

    
    @AfterMethod
    public void finalizeTest1() {
    	    	extent.flush();
    	
    }
    
    
    @BeforeMethod
    public void Test2() {
    	
    extent= ExtentManager.getReports();
    	   test = extent.createTest("Verify case1_Name is CarbonCredit");
    	
    }  
    /**
     * Acceptance criteria-1 Name equalsCarbon credits exist  test for a positive test
     */
	@Test(priority=2, testName ="Verify case1_Name is CarbonCredit")
	public void case1_NameCarbonCredit() { System.out.println("case1_NameCarbonCredit");
		int actualstatusCode = response.getStatusCode();
		try {
		AssertJUnit.assertEquals(actualstatusCode, StatusCodeConstants.Ok);
	    AssertJUnit.assertEquals(response.jsonPath().getString("Name"),"Carbon credits");
			    test.log(Status.INFO, "Verify case1_Name is CarbonCredit");
				test.log(Status.INFO, "Test inprogress ;");
		 } catch(Exception e) {  
			    test.log(Status.INFO, "Verify case1_Name is CarbonCredit");
				test.log(Status.INFO, "Test Exception ;"+e.getMessage());
		}
	}	
	  @AfterMethod
	    public void finalizeTest2() {
	    	extent.flush();
	    }	
	
	  @BeforeMethod
	    public void Test3() {
	    	
	    extent= ExtentManager.getReports();
	    	   test = extent .createTest("Verify can ReList..");
	   	
	    }	
	/**
	 * Acceptance criteria-2 CanRelist exist test for a positive test
	 */
	@Test(priority=3, testName ="Verify can ReList..")
	public void case2_CanRelist() { System.out.println("case2_CanRelist");
	int actualstatusCode = response.getStatusCode();
	 boolean expectedValue=true; 
			try {
					AssertJUnit.assertEquals(actualstatusCode, StatusCodeConstants.Ok);
					AssertJUnit.assertEquals(response.jsonPath().getBoolean("CanRelist"),expectedValue);
			} catch(Exception e) {  
			    		test.log(Status.INFO, "Verify case1_Name is CarbonCredit");
						test.log(Status.INFO, "Test Exception ;"+e.getMessage());
		    String jsonString = response.getBody().asString();
		    System.out.println("Response body =" + jsonString);
		 }
	}
	  @AfterMethod
	    public void finalizeTest3() {
	    	extent.flush();
	    }
	  
	  
	  @BeforeMethod
	    public void Test4() {
	    	
	    extent= ExtentManager.getReports();
	    	   test = extent .createTest("Verify Name Gallery contains text 2X Larger image in the description..");
	    	
	    }	  
/*** 
	 * The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
	 * this test is for a negative test
	 */
	  //
		@Test(priority=4, testName ="Verify Name Gallery contains text 2X Larger image in the description..")
		public void case3_Name_equal_Gallery_has_a_Description_that_contains_the_text_2x_larger_image() {

			String jsonString=response.getBody().asString();

			try {
				
				JsonElement fileElement= JsonParser.parseString(jsonString);
				JsonObject fileObject = fileElement.getAsJsonObject();
				JsonArray jsonArrayOfPromotions = fileObject.get("Promotions").getAsJsonArray();
						for (JsonElement promotionElement : jsonArrayOfPromotions) {
							JsonObject promotionObject = promotionElement.getAsJsonObject();
							String name = promotionObject.get("Name").getAsString();
							String description = promotionObject.get("Description").getAsString();
							System.out.println("Name=" + name);
							System.out.println("Description=" + description);
							boolean requiredTextContainsInTheDescription = false;
							try {
								
								 {
									if(name.equals("Gallery") && description.contains("2x larger image")){
										requiredTextContainsInTheDescription=true;
									}
										//throw new SkipException("Skipping this test data matching the condition is nonexisting");
								}
								 
								 SoftAssert softAssert =new SoftAssert();
								 AssertJUnit.assertTrue(requiredTextContainsInTheDescription);
								 softAssert.assertAll();
								 test.log(Status.INFO, "Verify Name Gallery contains text 2X Larger image in the description");
								 test.log(Status.INFO, "Test inprogress ;");
							} catch (AssertionError e) {
								 test.log(Status.INFO, "Verify Name Gallery contains text 2X Larger image in the description");
								 test.log(Status.INFO, "Test Exception ;");
								 test.log(Status.INFO, "Test case fails does not meet  the requirement that  name with Gallery has a Description that contains the text 2x larger image"+e.getMessage());
								System.out.println("...Test case fails does not meet  the requirement that  name with Gallery has a Description that contains the text 2x larger image");
							} catch(Throwable t){
								
								System.out.println("Throwing error.."+t.getMessage());
								
							}
				}
			
		} catch (JsonIOException e) {
				System.out.println("..IO error...");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		}
		
		@AfterMethod
	    public void finalizeTest4() {
	    	extent.flush();
	    }
	
	}
	

	
