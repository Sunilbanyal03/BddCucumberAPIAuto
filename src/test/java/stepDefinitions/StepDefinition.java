package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification respspec;
	Response respns;
	static String place_id;
	
	TestDataBuild td=new TestDataBuild();
	
	@Given("that Add Place Payload with {string} {string} {string}")
	public void that_add_place_payload_with(String name, String language, String address) throws IOException {
	  res=given().spec(requestSpecification())
			  .body(td.add_place_payload(name, language, address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		System.out.println("EXECUTING 1");
		
	  respspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	  
	  if(method.equalsIgnoreCase("POST"))
	  respns=res.when().post(resourceAPI.getResource());
	  else if(method.equalsIgnoreCase("GET"))
		  respns=res.when().get(resourceAPI.getResource());
	  
	  
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
		System.out.println("EXECUTING 2");
	   Assert.assertEquals(respns.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
	  
		System.out.println("EXECUTING 3");
	   Assert.assertEquals(getJsonPath(respns, keyValue),ExpectedValue);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		
		System.out.println("EXECUTING 4");
	  place_id= getJsonPath(respns, "place_id");
	   res=given().spec(requestSpecification()).queryParam("place_id",place_id);
	   user_calls_with_post_http_request(resource,"GET");
	   
	   String actualname=getJsonPath(respns, "name");
	   Assert.assertEquals(actualname,expectedname );
	}

	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	  res= given().spec(requestSpecification()).body(td.deletePlacepayload(place_id));
	   
	}

	

}
