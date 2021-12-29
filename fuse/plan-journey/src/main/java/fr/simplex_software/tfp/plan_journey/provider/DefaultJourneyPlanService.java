package fr.simplex_software.tfp.plan_journey.provider;

import fr.simplex_software.tfp.plan_journey.api.*;
import fr.simplex_software.tfp.plan_journey.model.*;

import javax.ws.rs.*;
import java.util.*;

public class DefaultJourneyPlanService implements RestJourneyPlanService
{
  @Path("/j1")
  @Override
  public List<Journey> getJourneys()
  {
    return null;
  }

  @Override
  public Journey getJourney(int journeyId)
  {
    return null;
  }

  @Override
  public void updateJourney(Journey journey)
  {

  }

  @Override
  public Journey createJourney(Journey journey)
  {
    return null;
  }

  @Override
  public void cancelJourney(int journeyId)
  {

  }
}
