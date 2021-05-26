package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.StatusCodeConstants;
import io.restassured.response.Response;


@Listeners(listeners.TestNGListeners.class)
public class TestCases  extends TestListenerAdapter {
    String BaseUrl = "https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false";
 /**
 * get all records for the provided url test for positive test
 * @author Jagath
 *
 */

    @Test
    public void case_RecordsgetAll() {
		
		 Response response = given().get(BaseUrl);
	        int actualstatusCode = response.getStatusCode();
	        String actualStatusLine=response.getStatusLine();
	        assertEquals(actualstatusCode, StatusCodeConstants.Ok);
	        assertEquals(actualStatusLine,"HTTP/1.1 200 OK");
	}

    /**
     * Acceptance criteria-1 Name equalsCarbon credits exist  test for a positive test
     */
	@Test
	public void case1_NameCarbonCredit() { System.out.println("case1_NameCarbonCredit");
	Response response = given().when().get(BaseUrl).then().extract().response();
	int actualstatusCode = response.getStatusCode();
	assertEquals(actualstatusCode, StatusCodeConstants.Ok);
    assertEquals(response.jsonPath().getString("Name"),"Carbon credits");
    }	
	
	/**
	 * Acceptance criteria-2 CanRelist exist test for a positive test
	 */

	@Test
	public void case2_CanRelist() { System.out.println("case2_CanRelist");
		Response response = given()
				.when().get(BaseUrl).then().extract().response();
	int actualstatusCode = response.getStatusCode();
	assertEquals(actualstatusCode, StatusCodeConstants.Ok);
    boolean expectedValue=true; 
    assertEquals(response.jsonPath().getBoolean("CanRelist"),expectedValue);
    String jsonString = response.getBody().asString();
    System.out.println("Response body =" + jsonString);
 }

/*** 
	 * The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
	 * this test is for a negative test
	 */
		@Test
		public void case3_Name_equal_Gallery_has_a_Description_that_contains_the_text_2x_larger_image() {
			System.out.println("case3...");
		Response response = given().get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");
		int actualstatusCode = response.getStatusCode();
		
		int nameLength=response.jsonPath().getString("Name").length();
		String description= response.jsonPath().getString("Description");
		int descriptionLength=description.length();
		System.out.println("Description"+response.jsonPath().getString("Description"));
		// since there was no image in the response urlToImage is null
		String urlToImage=null;
		Response responseImage = given().get(urlToImage);
		assertEquals(actualstatusCode, StatusCodeConstants.Ok);
		}
	}
	
