package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenerio() throws IOException {
		// Write a code that will give you placeid
		// Execute this code only when place id is null
		
		
		StepDefinition sd=new StepDefinition();
		
		if(StepDefinition.place_id==null)
		{
		sd.that_add_place_payload_with("poo", "French", "India");
		sd.user_calls_with_post_http_request("addPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("poo", "getPlaceAPI");
		}
	}

}
