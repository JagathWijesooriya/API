/*package testAutomationAPI.testAutomationAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.TestCase;
//import models.CategoryInfo;
import models.CategoryInfo;
import models.Promotion;

*//**
 * json list type test test for the App.
 *//*
public class AppTest 
    extends TestCase
{
	
	
	
	public static void main(String[] args) {
		String BaseUrl = "https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false";

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
*/