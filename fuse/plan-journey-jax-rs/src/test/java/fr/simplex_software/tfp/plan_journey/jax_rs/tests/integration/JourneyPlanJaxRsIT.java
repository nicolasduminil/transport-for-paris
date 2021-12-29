package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import com.github.database.rider.core.api.dataset.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.apache.http.*;
import org.junit.*;
import org.junit.runners.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JourneyPlanJaxRsIT extends TestCommons
{
  private static Client client;
  private static WebTarget webTarget;
  private final static String url = "http://localhost:18080/plan-journey-jax-rs/tfp/journeys";
  private static JourneyEntity journeyEntity;
  private static String gen;

  @BeforeClass
  public static void setUpBefore()
  {
    journeyEntity = (JourneyEntity) unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
    gen = new Random().ints(19, 123).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
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
  public void test0()
  {
    journeyEntity.setName(new StringBuilder(journeyEntity.getName()).append("_").append(gen).toString());
    Response response = webTarget.request().post(Entity.xml(journeyEntity));
    assertNotNull(response);
    assertEquals(HttpStatus.SC_CREATED, response.getStatus());
  }

  @Test
  public void test1()
  {
    Response response = webTarget.request().get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    List<JourneyDto> journeys = response.readEntity(new GenericType<>(){});
    assertNotNull(journeys);
    assertFalse (journeys.isEmpty());
  }

  @Test
  public void test2()
  {
    Response response = webTarget.path("1").request().get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    JourneyDto journey = response.readEntity(JourneyDto.class);
    assertNotNull(journey);
  }

  @Test
  public void test3()
  {
    Response response = webTarget.request().accept(MediaType.APPLICATION_XML).get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    List<JourneyDto> journeys = response.readEntity(new GenericType<>(){});
    assertNotNull(journeys);
    assertFalse(journeys.isEmpty());
  }

  @Test
  public void test4()
  {
    Response response = webTarget.path("1").request().accept(MediaType.APPLICATION_XML).get();
    assertEquals(HttpStatus.SC_OK, response.getStatus());
    JourneyDto journey = response.readEntity(JourneyDto.class);
    assertNotNull(journey);
  }
}
