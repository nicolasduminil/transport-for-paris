package fr.simplex_software.tfp.plan_journey.services.tests.unit;

import fr.simplex_software.tfp.plan_journey.facade.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import fr.simplex_software.tfp.plan_journey.repository.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestPlanJourneyService extends TestCommons
{
  @Mock
  private static PlanJourneyRepository mockPlanJourneyRepository;
  private PlanJourneyService planJourneyService;


  @Before
  public void before()
  {
    planJourneyService = new PlanJourneyService(new PlanJourneyFacade(mockPlanJourneyRepository));
  }

  @Test
  public void testCreateJourney()
  {
    when (mockPlanJourneyRepository.saveAndFlushAndRefresh(any (JourneyEntity.class))).thenReturn(getJourneyEntity());
    JourneyDto journeyDto = planJourneyService.createJourney(getJourneyDto());
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }

  @Test
  public void testGetJourneys()
  {
    when (mockPlanJourneyRepository.findAll()).thenReturn(List.of(getJourneyEntity()));
    List<JourneyDto> journeyDtoList = planJourneyService.getJourneys();
    assertThat(journeyDtoList).isNotEmpty();
    assertThat(journeyDtoList.size()).isEqualTo(1);
    JourneyDto journeyDto = journeyDtoList.get(0);
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }

  @Test
  public void testGetJourney()
  {
    when (mockPlanJourneyRepository.findBy(any(Long.class))).thenReturn(getJourneyEntity());
    JourneyDto journeyDto = planJourneyService.getJourney(1L);
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }
}
