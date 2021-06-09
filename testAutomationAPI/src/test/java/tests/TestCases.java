package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import constants.StatusCodeConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CategoryInfo;
import models.Promotion;

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
	 * this test is for a positive test
	 */
		//@Test
		public void case3_Name_equal_Gallery_has_a_Description_that_contains_the_text_2x_larger_image() {

			Response response = RestAssured.given().when().get(BaseUrl).andReturn();
			String jsonString=response.getBody().asString();

			try {
				
				
				Type PromotionListType = new TypeToken<ArrayList<Promotion>>(){}.getType();
				List<CategoryInfo>	promotions = new Gson().fromJson(jsonString,PromotionListType);
						JsonElement fileElement= JsonParser.parseString(jsonString);
				
				JsonObject fileObject = fileElement.getAsJsonObject();
					//	System.out.println("fileObject=" + fileObject.toString());

						JsonArray jsonArrayOfPromotions = fileObject.get("Promotions").getAsJsonArray();
						for (JsonElement promotionElement : jsonArrayOfPromotions) {
							JsonObject promotionObject = promotionElement.getAsJsonObject();

							String name = promotionObject.get("Name").getAsString();
							String description = promotionObject.get("Description").getAsString();
							System.out.println("Name=" + name);
							System.out.println("Description=" + description);

							// if (name.equals("Gallery")&&description.contains("2x larger image")) {}
							assertTrue(name.equals("Gallery") && description.contains("2x larger image"));
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
	}
	
