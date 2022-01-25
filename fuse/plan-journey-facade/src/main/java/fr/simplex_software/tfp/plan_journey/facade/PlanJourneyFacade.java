package fr.simplex_software.tfp.plan_journey.facade;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.repository.*;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.persistence.*;
import java.util.*;

@ApplicationScoped
public class PlanJourneyFacade
{
  @Inject
  private PlanJourneyRepository planJourneyRepository;

  public JourneyEntity createJourney(JourneyEntity journeyEntity)
  {
    return planJourneyRepository.saveAndFlushAndRefresh(journeyEntity);
  }

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

  public void removeJourney (JourneyEntity journeyEntity)
  {
    planJourneyRepository.remove(journeyEntity);
  }

  public Optional<JourneyEntity> getJourneyByName(String name)
  {
    return planJourneyRepository.findByName(name);
  }
}
