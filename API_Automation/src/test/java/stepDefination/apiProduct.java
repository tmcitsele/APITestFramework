package stepDefination;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiProduct {
	public static String identifier;
	public static Response resp;

	@Given("I have base {string} URL")
	public void i_have_base_url_for_api_testing_with_id(String URL) {
		RestAssured.baseURI = URL;
	}

	@And("I have API Testing with ID {string} for {string}")
	public void i_have_api_testing_with_id_for(String ID, String URL) {
		identifier = URL + "api/product/" + ID;
	}

	@When("I get the Product Information")
	public void i_get_the_product_information() {
		resp = RestAssured.get(identifier);

	}

	@Then("I validate the status code {string}")
	public void i_validate_the_status_code(String statuscode) {
		int code = resp.getStatusCode();
		System.out.println("Status code is " + code);
		Assert.assertEquals(code, Integer.parseInt(statuscode));
	}

	@And("I validate product Id and Description")
	public void i_validate_product_id_and_description() {
		String output = resp.asString();

		JsonPath jsonPath = new JsonPath(output);

		List<Map<String, Object>> products = jsonPath.getList("");
		for (Map<String, Object> product : products) {
			System.out.println("ID:" + product.get("id") + ",Product Name:" + product.get("productName"));
		}
	}

	@When("I will add new Product {string}  {string} {string} {string}")
	public void i_will_add_new_product(String productName, String productType, String description, String price) {
	
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("productName", productName);
		requestParams.put("productType", productType);
		requestParams.put("description", description);
		requestParams.put("price", price);

		request.header("Content-Type", "application/json");

		request.body(requestParams.toJSONString());
		
		Response response = request.post("/api/product/add");
		System.out.println("The status received: " + response.statusLine()); 
	}
}
