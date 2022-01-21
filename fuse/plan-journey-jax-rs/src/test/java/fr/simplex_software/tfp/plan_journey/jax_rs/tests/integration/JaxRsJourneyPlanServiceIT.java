package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import org.apache.deltaspike.jpa.api.entitymanager.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;
import java.io.*;
import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.wildfly.common.Assert.*;

@RunWith(Arquillian.class)
public class JaxRsJourneyPlanServiceIT
{
  @Inject
  private PlanJourneyService planJourneyService;
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;

  @Deployment
  public static WebArchive createDeployment()
  {
    return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/plan-journey-jax-rs.war"));
  }

  @Produces
  @Named("EntityManagerProducerEE")
  @RequestScoped
  public EntityManager create()
  {
    if (entityManagerFactory == null)
      Persistence.createEntityManagerFactory("paris-oracle");
    return this.entityManagerFactory.createEntityManager();
  }

  public void dispose(@Disposes @Named("EntityManagerProducerEE") EntityManager entityManager)
  {
    if (entityManager.isOpen())
    {
      entityManager.close();
    }
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
  public void test0()
  {
    System.out.println ("### finalUri: " + finalUri.toString());
    //assertNotNull(planJourneyService);
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
  public void test0()
  {
    assertNotNull(planJourneyService);
  }

  @Test
  public void test1()
  {
    MetadataDto metadataDto = new MetadataDto("metadataCall", LocalDateTime.now(), "metadataVersion");
    List<DestinationDto> destinationDtos = List.of(new DestinationDto("stationName", "platformId"));
    ResultDto resultDto = new ResultDto(destinationDtos);
    JourneyDto journeyDto = planJourneyService.createJourney(new JourneyDto("MyJourney", resultDto, metadataDto));
    assertNotNull(journeyDto);
    assertNotNull(journeyDto.getResult());
    assertNotNull(journeyDto.getMetadata());
    assertThat(journeyDto.getMetadata().getMetadataCall()).isEqualTo("metadataCall");
    assertThat(journeyDto.getResult().getDestinations().get(0).getStationName()).isEqualTo("stationName");
    assertThat(journeyDto.getResult().getDestinations().get(0).getPlatformId()).isEqualTo("platformId");
  }
}
