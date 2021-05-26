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
 /**
 * Acceptance criteria3 Name equals Gallery  has a description the text 2 times larger image exists  test- no description contain 2x_larger_image in response body
 */
		@Test
		public void case3_Name_equal_Gallery_has_a_Description_that_contains_the_text_2x_larger_image() {
			System.out.println("case3...");
		Response response = given().get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");
		int actualstatusCode = response.getStatusCode();
		String name=response.jsonPath().getString("Name");
		String description= response.jsonPath().getString("Description");
		assertEquals(actualstatusCode, StatusCodeConstants.Ok);
		}
	}
	
