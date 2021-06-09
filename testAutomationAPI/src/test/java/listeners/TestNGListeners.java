package listeners;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import constants.StatusCodeConstants;
import io.restassured.response.Response;
import tests.BaseTests;

/**
 * listener to monitor the test status from the inception to fail, skip, success till finish
 * @author Jagath
 *
 */
public class TestNGListeners extends BaseTests implements ITestListener
{
	

	public void onFinish(ITestResult result) {
			System.out.println("\r\n" + "	test finished==..");
		
	}

	public void onStart(ITestResult result) {
			System.out.println("\r\n" + "	test on start==.."+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 Response response = given().get(BaseTests.BaseUrl);
	        int statusCode = response.getStatusCode();
	        assertEquals(statusCode, StatusCodeConstants.Ok);
	        assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
			System.out.println("\r\n" + "	test==.."+result.getName()+result.FAILURE);
			System.out.println("Response recieved is:"+response.getBody().asString());
	}

	public void onTestFailure(ITestResult result) { System.out.println("...test.."+result.getName()+" on failure...");
		Response response = given().get(BaseTests.BaseUrl);
		System.out.println("\r\n" + "	test ==.."+result.getName()+"   "+result.FAILURE);
		System.out.println("Response recieved is:"+response.getBody().asString());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("...test "+result.getName()+" on skipped...");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("\r\n" + "	on test "+result.getName()+"Start..."+result.STARTED);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("\r\n" + "	test "+result.getName()+" on success==.."+result.SUCCESS);
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("...test " +context.getName()+" on finished..."+context.getPassedTests());
	}

	public void onStart(ITestContext context) {
		System.out.println("...test "+context.getName()+"on start..."+context.getName());
	}
}
