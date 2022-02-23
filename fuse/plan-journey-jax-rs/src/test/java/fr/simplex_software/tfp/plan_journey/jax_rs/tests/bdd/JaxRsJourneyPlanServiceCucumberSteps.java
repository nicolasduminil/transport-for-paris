package fr.simplex_software.tfp.plan_journey.jax_rs.tests.bdd;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import io.cucumber.java.en.*;
import io.restassured.*;
import org.apache.http.*;

import javax.ws.rs.core.*;
import java.net.*;

import static org.assertj.core.api.Assertions.*;

public class JaxRsJourneyPlanServiceCucumberSteps extends TestCommons
{
  private static URI finalUri;
  private io.restassured.response.Response response;
  private final JourneyDto journeyDto = TestCommons.getJourneyDto();
  private static String name;

  @Given("URI is initialized")
  public void uriIsInitialized()
  {
    finalUri = UriBuilder.fromPath("plan-journey-jax-rs")
      .scheme("http")
      .host("localhost")
      .port(18080)
      .path("tfp")
      .path("journeys")
      .build();
  }

  @When("user wants to create journey")
  public void userWantsToCreateJourney()
  {
     response =
      RestAssured.given()
        .accept(MediaType.APPLICATION_XML)
        .contentType(MediaType.APPLICATION_XML)
        .body(journeyDto)
        .when()
        .post(finalUri);
  }

  @Then("journey is successfully created")
  public void customerIsSuccessfullyCreated()
  {
    response.then()
      .statusCode(HttpStatus.SC_CREATED);
  }

  @When("user wants to get journeys list")
  public void userWantsToGetJourneyList()
  {
    response = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(finalUri);
  }

  @Then("journeys list is returned")
  public void journeyListIsReturned()
  {
    response.then()
      .statusCode(HttpStatus.SC_OK);
  }

  @When("user wants to get journey associated with name")
  public void userWantsToGetCustomerAssociatedWithId()
  {
    response = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(UriBuilder.fromUri(finalUri).path("ref").path("{journeyName}").build(journeyDto.getName()));
  }

  @Then("journey is returned")
  public void customerIsReturned()
  {
    response.then()
      .statusCode(HttpStatus.SC_OK);
    assertThat(response.as(JourneyDto.class).getName()).startsWith("MyJourney");
  }

  @When("user wants to update journey")
  public void userWantsToUpdateJourney()
  {
    journeyDto.getMetadata().setMetadataCall("meta123");
    response =     RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .body(journeyDto)
      .when()
      .put(finalUri);
  }

  @Then("journey is updated")
  public void journeyIsUpdated()
  {
    response.then()
      .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  @When("user wants to remove journey")
  public void userWantsToRemoveCustomerWithId()
  {
    response = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(UriBuilder.fromUri(finalUri).path("id").path("{journeyName}").build("MyJourney822"));
    String str = response.asString();
    System.out.println("### str: " + str);
    Long id = Long.parseLong(str);
    response = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .delete(UriBuilder.fromUri(finalUri).path("{id}").build(id));
  }

  @Then("journey is removed")
  public void customerIsRemoved()
  {
    response.then()
      .statusCode(HttpStatus.SC_NO_CONTENT);
  }
}
