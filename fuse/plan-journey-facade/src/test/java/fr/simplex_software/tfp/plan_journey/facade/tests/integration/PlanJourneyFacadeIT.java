package fr.simplex_software.tfp.plan_journey.facade.tests.integration;

import fr.simplex_software.tfp.plan_journey.facade.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.apache.deltaspike.testcontrol.api.junit.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import javax.inject.*;
import javax.ws.rs.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanJourneyFacadeIT extends TestCommons
{
  private static JourneyEntity journeyEntity;
  @Inject
  private PlanJourneyFacade planJourneyFacade;

  @BeforeClass
  public static void beforeClass()
  {
    journeyEntity = (JourneyEntity) unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
  }

  @Test
  public void test0()
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
    assertFalse(journeyEntity.getResult().getDestinations().isEmpty());
    assertNotNull(journeyEntity.getMetadata().getJourney());
  }

  @Test
  public void test1()
  {
    JourneyEntity je = planJourneyFacade.createJourney(new JourneyEntity(new JourneyDto(journeyEntity)));
    assertNotNull(je);
    assertNotNull(je.getMetadata());
    assertNotNull(je.getResult());
    assertEquals("metadataCall", je.getMetadata().getMetadataCall());
  }

  @Test
  public void test2()
  {
    JourneyEntity je = planJourneyFacade.getJourneyByName("MyJourney").orElseThrow(() -> new NotFoundException("The Journey having the name \"MyJourney\" does not exist"));
    assertNotNull(je);
    assertEquals("MyJourney", je.getName());
  }

  @Test
  public void test4()
  {
    List<JourneyEntity> journeys = planJourneyFacade.getJourneys();
    assertNotNull (journeys);
    assertEquals (1, journeys.size());
  }

  @Test
  public void test6()
  {
    planJourneyFacade.removeJourney(journeyEntity);
    assertTrue(planJourneyFacade.getJourneys().isEmpty());
  }
}
