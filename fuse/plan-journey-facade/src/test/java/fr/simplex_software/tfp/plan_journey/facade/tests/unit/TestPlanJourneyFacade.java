package fr.simplex_software.tfp.plan_journey.facade.tests.unit;

import fr.simplex_software.tfp.plan_journey.facade.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import fr.simplex_software.tfp.plan_journey.repository.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestPlanJourneyFacade extends TestCommons
{
  private static JourneyEntity journeyEntity;

  @Mock
  private PlanJourneyRepository planJourneyRepository;

  @BeforeClass
  public static void beforeClass()
  {
    journeyEntity = (JourneyEntity) unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
  }

  @Test
  public void testGetJourneyByName()
  {
    when (planJourneyRepository.findByName("MyJourney")).thenReturn (Optional.of(journeyEntity));
    assertEquals ("MyJourney", new PlanJourneyFacade(planJourneyRepository).getJourneyByName("MyJourney").orElse(journeyEntity).getName());
    verify(planJourneyRepository).findByName("MyJourney");
  }

  @Test
  public void testCreateJourney()
  {
    when (planJourneyRepository.saveAndFlushAndRefresh(any(JourneyEntity.class))).thenReturn (journeyEntity);
    assertEquals ("MyJourney", new PlanJourneyFacade(planJourneyRepository).createJourney(journeyEntity).getName());
    verify(planJourneyRepository).saveAndFlushAndRefresh(journeyEntity);
  }

  @Test
  public void testUpdateJourney()
  {
    when (planJourneyRepository.merge(any(JourneyEntity.class))).thenReturn (journeyEntity);
    assertEquals ("MyJourney", new PlanJourneyFacade(planJourneyRepository).updateJourney(journeyEntity).getName());
    verify(planJourneyRepository).merge(journeyEntity);
  }

  @Test
  public void testGetJourneys()
  {
    when (planJourneyRepository.findAll()).thenReturn (List.of(journeyEntity));
    assertEquals ("MyJourney", new PlanJourneyFacade(planJourneyRepository).getJourneys().get(0).getName());
    verify(planJourneyRepository).findAll();
  }

  @Test
  public void testGetJourney()
  {
    when (planJourneyRepository.findBy(any(Long.class))).thenReturn (journeyEntity);
    assertEquals ("MyJourney", new PlanJourneyFacade(planJourneyRepository).getJourney(1L).orElse(journeyEntity).getName());
    verify(planJourneyRepository).findBy(Long.valueOf(1));
  }

  @Test
  public void testRemoveJourney()
  {
    when(planJourneyRepository.findByName("MyJourney")).thenReturn(Optional.of(journeyEntity));
    doNothing().when(planJourneyRepository).remove(any(JourneyEntity.class));
    new PlanJourneyFacade(planJourneyRepository).removeJourney(journeyEntity);
    verify(planJourneyRepository).remove(journeyEntity);
  }
}
