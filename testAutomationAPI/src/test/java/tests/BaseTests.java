package tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import helpers.RestAssuredHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTests {
	static String defaultUrl = "https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json";
	public static String BaseUrl = defaultUrl;
	protected static ExtentReports reports;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected static Response response;
	private static String resultpath;
	protected static String ReportLocation;

	public static String getReportLocation() {
		return ReportLocation;
	}

	RestAssuredHelper restAssuredHelper;

	public BaseTests() {
		setup();
		restAssuredHelper = new RestAssuredHelper();
	}

	/**
	 * test environment paremeter setup
	 * 
	 * @param api.uri
	 * @param api.port
	 * @param report.Location
	 */
	public void setup() {
		try {
			// load Test API Url from test environment properties file.
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
			String providedEndpoint = props.getProperty("api.uri");
			String providedPort = props.getProperty("api.port");
			if (providedEndpoint.isEmpty() || providedEndpoint != null) {
				BaseUrl = providedEndpoint;
			} else {
				BaseUrl = defaultUrl;
			}
			if (!props.getProperty("api.port").isEmpty()) {
				RestAssured.port = Integer.valueOf(props.getProperty("api.port"));
			}

			if (!props.getProperty("report.Location").isEmpty()) {
				resultpath = getResultPath();
				ReportLocation = "test-output/Report/" + resultpath + "/";
			} else {
				ReportLocation = props.getProperty("report.Location");
			}
			RestAssured.baseURI = BaseUrl;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		getResponse();
	}

	private static String getResultPath() {

		resultpath = new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	public Response getResponse() {
		Response response = given().when().get(BaseUrl).then().extract().response();

		System.out.println("###Response=##" + response);
		if (response == null) {
			System.out.println(
					"** When service is not available  ... Response data picking from default response json data file stored in the project directotry******");

			try {
				JsonElement fileElement = null;
				String jsonFileName = "catagory.json";
				try {
					fileElement = JsonParser.parseReader(new FileReader("./catagory.json"));
				} catch (FileNotFoundException e) {
					System.out.println("..IO error.json.File." + jsonFileName + "Not Available");
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
		return response;
		// get response from default response json datafile

	}
	// }

	/**
	 * cleanup test data
	 */
	public void teardown() {

	}

	public String getBaseUrl() {
		return this.BaseUrl;
	}
}
