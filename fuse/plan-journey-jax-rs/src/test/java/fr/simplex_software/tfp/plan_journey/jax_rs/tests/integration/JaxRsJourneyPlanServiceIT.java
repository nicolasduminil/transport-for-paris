package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import org.apache.http.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.inject.*;
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

  @Deployment
  public static WebArchive createDeployment()
  {
    /*System.out.println ("######################################");
    File[] files = Maven.resolver().loadPomFromFile("pom.xml")
      .importDependencies(ScopeType.COMPILE, ScopeType.TEST, ScopeType.PROVIDED, ScopeType.RUNTIME)
      .resolve().withTransitivity().asFile();
    for (File f : files)
      for (String fileName : Objects.requireNonNull(f.list()))
          System.out.println ("### " + fileName);
    return ShrinkWrap.create(ZipImporter.class, "pj.war")
      .importFrom(new File("target/plan-journey-jax-rs.war")).as(WebArchive.class);*/
    WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/plan-journey-jax-rs.war"));
    /*File props = new File("src/test/resources/META-INF","apache-deltaspike.properties");
    if (props == null)
      System.out.println ("### Props missing");
    else
      System.out.println ("### Props okay " + props.getAbsolutePath());
    war.addAsWebInfResource(props);*/
    return war;
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

  /*@Before
  public void before()
  {
    finalUri = UriBuilder.fromPath("plan-journey-jax-rs")
      .scheme("http")
      .host("localhost")
      .port(18080)
      .path("tfp")
      .path("journeys")
      .build();
  }

  @Test
  public void test1()
  {
    RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .body(journeyEntity)
      .when()
      .post(finalUri)
      .then()
      .statusCode(HttpStatus.SC_CREATED)
      .extract()
      .header("Location");
  }

  @Test
  public void test2() throws JAXBException
  {
    List<JourneyDto> journeyDtos = new ArrayList<JourneyDto>();
    String xml = RestAssured.given()
      .accept(MediaType.APPLICATION_XML)
      .contentType(MediaType.APPLICATION_XML)
      .when()
      .get(finalUri)
      .then()
      .statusCode(HttpStatus.SC_OK)
      .extract()
      .body()
      .asString();
    journeyDtos = (List<JourneyDto>) JAXBContext.newInstance(journeyDtos.getClass()).createUnmarshaller()
      .unmarshal(new StringReader(xml));
    assertThat(journeyDtos).isNotNull();
    assertThat(journeyDtos.size()).isEqualTo(1);
    assertThat(journeyDtos.get(0).getName()).isEqualTo("MyJourney");
  }*/

  @Test
  @RunAsClient
  public void test0()
  {
    MetadataDto metadataDto = new MetadataDto("metadataCall", LocalDateTime.now(), "metadataVersion");
    List<DestinationDto> destinationDtos = List.of(new DestinationDto("stationName", "platformId"));
    ResultDto resultDto = new ResultDto(destinationDtos);
    Response response = webTarget.request().post(Entity.entity(new JourneyDto("MyJourney822", resultDto, metadataDto), "application/xml"));
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

  @Test
  public void test10()
  {
    MetadataDto metadataDto = new MetadataDto("metadataCall", LocalDateTime.now(), "metadataVersion");
    List<DestinationDto> destinationDtos = List.of(new DestinationDto("stationName", "platformId"));
    ResultDto resultDto = new ResultDto(destinationDtos);
    JourneyDto journeyDto = planJourneyService.createJourney(new JourneyDto("MyJourney", resultDto, metadataDto));
    assertNotNull(journeyDto);
    assertNotNull(journeyDto.getResult());
    assertNotNull(journeyDto.getMetadata());
    assertEquals("metadataCall", journeyDto.getMetadata().getMetadataCall());
    assertEquals("stationName", journeyDto.getResult().getDestinations().get(0).getStationName());
    assertEquals("platformId", journeyDto.getResult().getDestinations().get(0).getPlatformId());
  }
}
