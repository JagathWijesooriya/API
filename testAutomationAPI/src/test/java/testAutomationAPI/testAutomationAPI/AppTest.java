/*package testAutomationAPI.testAutomationAPI;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class AppTest {

	public static void main(String[] args) {
		try {
			JsonElement fileElement = null;
			String jsonFileName="catagory.json";
			try {
				fileElement = JsonParser.parseReader(new FileReader("./catagory.json"));
			} catch (FileNotFoundException e) {
				System.out.println("..IO error.json.File."+jsonFileName+ "Not Available");
				e.printStackTrace();
			}

			JsonObject fileObject = fileElement.getAsJsonObject();
			;
			System.out.println("fileObject=" + fileObject.toString());

			JsonArray jsonArrayOfPromotions = fileObject.get("Promotions").getAsJsonArray();
			for (JsonElement promotionElement : jsonArrayOfPromotions) {
				JsonObject promotionObject = promotionElement.getAsJsonObject();

				String name = promotionObject.get("Name").getAsString();
				String description = promotionObject.get("Description").getAsString();
				System.out.println("Name=" + name);
				System.out.println("Description=" + description);

				// if (name.equals("Gallery")&&description.contains("2x larger image")) {}
				// assertTrue(name.equals("Gallery") && description.contains("2x larger
				// image"));
			}
		} catch (JsonIOException e) {

			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
			// TODO Auto-generated method stub

	}


*/