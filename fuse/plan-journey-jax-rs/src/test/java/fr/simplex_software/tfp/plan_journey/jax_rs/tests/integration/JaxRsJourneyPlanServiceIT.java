package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import io.restassured.*;
import org.apache.http.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.arquillian.test.spi.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.time.*;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class JaxRsJourneyPlanServiceIT
{
  @Inject
  private PlanJourneyService planJourneyService;
  private static final String url = "http://localhost:18080/plan-journey-jax-rs/tfp/journeys";
  private Client client;
  private WebTarget webTarget;
  private static JourneyDto journeyDto;
  private Long id;

  @Deployment
  public static WebArchive createDeployment()
  {
    return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/plan-journey-jax-rs.war"));
  }

  @BeforeClass
  public static void beforeClass()
  {
    journeyDto = getJourneyDto();
  }

  @AfterClass
  public static void afterClass()
  {
    journeyDto = null;
  }


  @Before
  public void setUp()
  {
    client = ClientBuilder.newClient();
    webTarget = client.target(url);
  }

  @After
  public void tearDown()
  {
    if (client != null)
    {
      client.close();
      client = null;
    }
    webTarget = null;
  }

  @Test
  @RunAsClient
  public void test0()
  {
    Response response = webTarget.request().post(Entity.entity(journeyDto, "application/xml"));
    assertNotNull(response);
    assertEquals(HttpStatus.SC_CREATED, response.getStatus());
  }

  @Test
  @RunAsClient
  public void test1()
  {
    Response response = webTarget.request().get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    List<JourneyDto> journeys = response.readEntity(new GenericType<>(){});
    assertNotNull(journeys);
    assertFalse(journeys.isEmpty());
    assertTrue(journeys.get(0).getName().startsWith("MyJourney"));
  }

  @Test
  @RunAsClient
  public void test2()
  {
    Response response = webTarget.path("1").request().get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    JourneyDto journey = response.readEntity(JourneyDto.class);
    assertNotNull(journey);
  }

  @Test
  @RunAsClient
  public void test3()
  {
    Response response = webTarget.request().accept(MediaType.APPLICATION_XML).get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    List<JourneyDto> journeys = response.readEntity(new GenericType<>(){});
    assertNotNull(journeys);
    assertFalse(journeys.isEmpty());
  }

  @Test
  @RunAsClient
  public void test4()
  {
    Response response = webTarget.path("1").request().accept(MediaType.APPLICATION_XML).get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    JourneyDto journey = response.readEntity(JourneyDto.class);
    assertNotNull(journey);
  }


  @Test
  @RunAsClient
  public void test5()
  {
    Response response = webTarget.path("1").request().accept(MediaType.APPLICATION_XML).delete();
    assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatus());
  }

  @Test(expected = ProcessingException.class)
  public void test60()
  {
    Response response = webTarget.request().get();
  }

  @Test(expected= ArquillianProxyException.class)
  public void test61()
  {
    planJourneyService.getJourneys();
  }

  @Test
  public void test7()
  {
    journeyDto = getJourneyDto();
    JourneyDto journeyDto2 = planJourneyService.createJourney(journeyDto);
    assertNotNull(journeyDto2);
    assertNotNull(journeyDto.getResult());
    assertNotNull(journeyDto.getMetadata());
    assertEquals("metadataCall", journeyDto2.getMetadata().getMetadataCall());
    assertEquals("stationName", journeyDto2.getResult().getDestinations().get(0).getStationName());
    assertEquals("platformId", journeyDto2.getResult().getDestinations().get(0).getPlatformId());
  }

  @Test
  public void test8()
  {
    JourneyDto journey = planJourneyService.findByName("MyJourney822");
    assertNotNull(journey);
    assertEquals("MyJourney822", journey.getName());
  }

  @Test
  public void test9()
  {
    List<JourneyDto> journeyDtos = planJourneyService.getJourneys();
    assertNotNull(journeyDtos);
    assertEquals(1, journeyDtos.size());
  }

  @Test
  public void testA()
  {
    journeyDto.getMetadata().setMetadataCall("metadataCall100");
    planJourneyService.updateJourney(journeyDto);
    JourneyDto journeyDto2 = planJourneyService.findByName("MyJourney822");
    assertEquals("metadataCall100", journeyDto2.getMetadata().getMetadataCall());
  }

  @Test
  public void testB()
  {
    planJourneyService.removeJourney(journeyDto);
  }

  private static JourneyDto getJourneyDto()
  {
    MetadataDto metadataDto = new MetadataDto("metadataCall", LocalDateTime.now(), "metadataVersion");
    List<DestinationDto> destinationDtos = List.of(new DestinationDto("stationName", "platformId"));
    ResultDto resultDto = new ResultDto(destinationDtos);
    return new JourneyDto("MyJourney822", resultDto, metadataDto);
  }

  @Test
  @RunAsClient
  public void testD()
  {
    RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .body(journeyDto)
      .when()
      .post(url)
      .then()
      .statusCode(HttpStatus.SC_CREATED)
      .extract()
      .header("Location");
  }

  @Test
  @RunAsClient
  public void testE()
  {
    List<JourneyDto> journeyDtos = new ArrayList<JourneyDto>();
    String xml = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(url)
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .body()
      .asString();
    assertEquals("<name>MyJourney822", xml.substring(xml.indexOf("<name>"), xml.indexOf("</name")));
  }

  @Test
  @RunAsClient
  public void testF()
  {
    String xml = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(UriBuilder.fromUri(url).path("ref/{journeyName}").build("MyJourney822"))
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .body()
      .asString();
    assertNotNull(xml);
    assertEquals("<name>MyJourney822", xml.substring(xml.indexOf("<name>"), xml.indexOf("</name")));
  }

  @Test
  @RunAsClient
  public void testG()
  {
    journeyDto.getMetadata().setMetadataCall("meta123");
    RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .body(journeyDto)
      .when()
      .put(url)
      .then()
      .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  @RunAsClient
  public void testH()
  {
    Long id = Long.parseLong(RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(UriBuilder.fromUri(url).path("id/{journeyName}").build("MyJourney822"))
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .body()
      .asString());
    RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .delete(UriBuilder.fromUri(url).path("{id}").build(id))
      .then()
      .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  @RunAsClient
  public void testI()
  {
    /*String xml = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(UriBuilder.fromUri(url).path("destinations/{type}/{line}").build(TransportType.SUBWAY.name(), "8"))
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .body()
      .asString();
    assertNotNull(xml);
    System.out.println (">>> xml: " + xml);
    assertEquals("<name>MyJourney822", xml.substring(xml.indexOf("<name>"), xml.indexOf("</name")));*/
  }
}
