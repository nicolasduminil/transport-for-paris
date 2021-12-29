package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import org.apache.deltaspike.testcontrol.api.junit.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import javax.inject.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Entities2IT extends TestCommons
{
  @Inject
  private PlanJourneyService planJourneyService;
  private static JourneyEntity journeyEntity;

  @BeforeClass
  public static void setup()
  {
    journeyEntity = (JourneyEntity) unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
  }


  @Test
  public void test0()
  {
    assertNotNull(planJourneyService);
  }

  @Test
  public void test1()
  {
    assertNotNull(journeyEntity);
    assertNotNull(journeyEntity.getResult());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult().getJourney());
    assertEquals (0, journeyEntity.getResult().getId().intValue());
    assertNotNull(journeyEntity.getResult().getDestinations());
    assertNotNull(journeyEntity.getMetadata().getJourney());
    JourneyDto journeyDto = new JourneyDto(journeyEntity);
    journeyEntity = new JourneyEntity(journeyDto);
    assertNotNull(journeyEntity);
    assertNotNull(journeyEntity.getResult());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult().getJourney());
    assertNotNull(journeyEntity.getResult().getDestinations());
    assertNotNull(journeyEntity.getMetadata().getJourney());
  }

  @Test
  public void test2()
  {
    JourneyDto journeyDto = planJourneyService.createJourney(new JourneyDto(journeyEntity));
    assertNotNull(journeyDto);
    assertNotNull(journeyDto.getResult());
    assertNotNull(journeyDto.getMetadata());
    assertEquals("metadataCall", journeyDto.getMetadata().getMetadataCall());
    assertEquals("stationName1", journeyDto.getResult().getDestinations().get(0).getStationName());
    assertEquals("platformId2", journeyDto.getResult().getDestinations().get(1).getPlatformId());
  }

  @Test
  public void test3()
  {
    JourneyDto journey = planJourneyService.findByName("MyJourney");
    assertNotNull(journey);
    assertEquals("MyJourney", journey.getName());
  }

  @Test
  public void test4()
  {
    List<JourneyDto> journeyDtos = planJourneyService.getJourneys();
    assertNotNull (journeyDtos);
    assertEquals (1, journeyDtos.size());
  }

  @Test
  public void test5()
  {
    JourneyDto journeyDto = new JourneyDto(journeyEntity);
    journeyDto.getMetadata().setMetadataCall("metadataCall100");
    planJourneyService.updateJourney(journeyDto);
    JourneyDto journeyDto2 = planJourneyService.findByName("MyJourney");
    assertEquals("metadataCall100", journeyDto2.getMetadata().getMetadataCall());
  }

  @Test
  public void test6()
  {
    planJourneyService.removeJourney(new JourneyDto(journeyEntity));
  }
}
