package com.signalpath.stepdefs;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherStepDef {
	
	private static final String BASE_URL = "https://api.weather.gov";

	private static Response response;

	@When("I invoke the forecast API")
	public void i_invoke_the_forecast_api() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		response = request.get("points/39.7456,-97.0892");
	}

	@Then("I validate the api response is successful")
	public void i_validate_the_api_response_is_successful() {
		Assert.assertEquals(200, response.getStatusCode());

	}
	
	@Then("I validate the timezone")
	public void i_validate_the_timezone() {
		String jsonString = response.asString();
		String actualTimeZone = JsonPath.from(jsonString).get("properties.timeZone");
		String expTimeZone = "America/Chicago";
		Assert.assertEquals(actualTimeZone, expTimeZone, "Timezone validation failed : actual Time zone = "+actualTimeZone);
	}

}
