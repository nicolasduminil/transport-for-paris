package fr.simplex_software.tfp.plan_journey.service;

import fr.simplex_software.tfp.plan_journey.facade.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.apache.deltaspike.jpa.api.transaction.*;

import javax.inject.*;
import javax.ws.rs.*;
import java.util.*;
import java.util.stream.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Transactional
@Slf4j
public class PlanJourneyService
{
  @Inject
  private PlanJourneyFacade planJourneyFacade;

  public JourneyDto createJourney(JourneyDto journeyDto)
  {
    planJourneyFacade.createJourney(new JourneyEntity(journeyDto));
    return journeyDto;
  }

  public JourneyDto updateJourney(JourneyDto journeyDto)
  {
    String name = journeyDto.getName();
    JourneyEntity journeyEntity = planJourneyFacade.getJourneyByName(name)
      .orElseThrow(() -> new NotFoundException(">>> The journey entity having the name "
        + name + " has not been found at this time in the data store"));
    planJourneyFacade.updateJourney(journeyEntity.fromDto(journeyDto));
    return journeyDto;
  }

  public List<JourneyDto> getJourneys()
  {
    List<JourneyEntity> journeyEntities = planJourneyFacade.getJourneys();
    if (journeyEntities.isEmpty())
      throw new NotFoundException(">>> No journey entity instance found at this time in the data store");
    return journeyEntities.stream().map(JourneyDto::new).collect(Collectors.toList());
  }

  public JourneyDto getJourney(Long id)
  {
    return new JourneyDto(planJourneyFacade.getJourney(id)
      .orElseThrow(() -> new NotFoundException(">>> The journey entity having the ID "
        + id + " has not be found at this time in the data store")));
  }

  public void removeJourney(JourneyDto journeyDto)
  {
    planJourneyFacade.removeJourney(new JourneyEntity(journeyDto));
  }

  public JourneyDto findByName(String name)
  {
    return new JourneyDto(planJourneyFacade.getJourneyByName(name)
      .orElseThrow(() -> new NotFoundException(">>> The journey entity having the name "
        + name + " has not been found at this time in the data store")));
  }

  public Long getJourneyIdByName(String name)
  {
    JourneyEntity journeyEntity = planJourneyFacade.getJourneyByName(name).orElseThrow(() -> new NotFoundException(">>> The journey entity having the name "
      + name + " has not been found at this time in the data store"));
    return journeyEntity.getId();
  }
}
