package fr.simplex_software.tfp.plan_journey.provider;

import fr.simplex_software.tfp.plan_journey.api.*;
import fr.simplex_software.tfp.plan_journey.model.*;
import lombok.extern.slf4j.*;

import java.util.*;

@Slf4j
public class JourneyPlanService implements RestJourneyPlanService
{
  @Override
  public List<Journey> getJourneys()
  {
    log.info(">>> JourneyPlanService.getJourneys()");
    return new ArrayList<Journey>();
  }

  @Override
  public Journey getJourney(int journeyId)
  {
    log.info(">>> JourneyPlanService.getJourney({})", journeyId);
    return null;
  }

  @Override
  public void updateJourney(Journey journey)
  {
    log.info(">>> JourneyPlanService.updateJourney({})", journey);
  }

  @Override
  public Journey createJourney(Journey journey)
  {
    log.info(">>> JourneyPlanService.createJourney({})", journey);
    return null;
  }

  @Override
  public void cancelJourney(int journeyId)
  {
    log.info(">>> JourneyPlanService.updateJourney({})", journeyId);
  }
}
