package tests;

import java.io.IOException;
import java.util.Properties;

import helpers.RestAssuredHelper;
import io.restassured.RestAssured;

public class BaseTests 
{   static String defaultUrl="https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json";
	public static String BaseUrl = defaultUrl;
	RestAssuredHelper restAssuredHelper;
	
	public BaseTests()
	{
		setup();
		restAssuredHelper = new RestAssuredHelper();
	}
	/**
	 * test environment setup
	 */
	public void setup() 
	{
			 try {
				 //load Test API Url from test environment properties file.
		        Properties props = new Properties();
		        props.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
		        String providedEndpoint=props.getProperty("api.uri");
		        String providedPort=props.getProperty("api.port");
		         if(providedEndpoint.isEmpty()||providedEndpoint!=null) {
		        	 BaseUrl=providedEndpoint;
		         }else {
		        	 BaseUrl=defaultUrl;
		         }
		         if(!props.getProperty("api.port").isEmpty())
		         {
		         RestAssured.port = Integer.valueOf(props.getProperty("api.port"));
		         }
		        RestAssured.baseURI = BaseUrl;
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }

	}
	
	/**
	 * cleanup test data
	 */
	public void teardown() {
		
	}
	
	public String getBaseUrl() {
		return this.BaseUrl;
	}
}
