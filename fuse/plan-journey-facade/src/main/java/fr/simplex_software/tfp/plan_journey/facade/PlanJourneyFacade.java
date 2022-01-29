package fr.simplex_software.tfp.plan_journey.facade;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.repository.*;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.transaction.*;
import javax.ws.rs.*;
import java.util.*;

@ApplicationScoped
public class PlanJourneyFacade
{
  @Inject
  private PlanJourneyRepository planJourneyRepository;

  @Transactional
  public JourneyEntity createJourney(JourneyEntity journeyEntity)
  {
    return planJourneyRepository.saveAndFlushAndRefresh(journeyEntity);
  }

  @Transactional
  public JourneyEntity updateJourney (JourneyEntity journeyEntity)
  {
    return planJourneyRepository.merge(journeyEntity);
  }

  public List<JourneyEntity> getJourneys()
  {
    return planJourneyRepository.findAll();
  }

  public Optional<JourneyEntity> getJourney (Long id)
  {
    return Optional.ofNullable(planJourneyRepository.findBy(id));
  }

  @Transactional
  public void removeJourney (JourneyEntity journeyEntity)
  {
    String name = journeyEntity.getName();
    Optional<JourneyEntity> je = planJourneyRepository.findByName(name);
    planJourneyRepository.remove(je.orElseThrow(() -> new NotFoundException(">>> The journey entity having the name "
      + name + " has not been found at this time in the data store")));
  }

  public Optional<JourneyEntity> getJourneyByName(String name)
  {
    return planJourneyRepository.findByName(name);
  }
}
