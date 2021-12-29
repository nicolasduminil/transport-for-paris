package fr.simplex_software.tfp.plan_journey.api;

import fr.simplex_software.tfp.plan_journey.model.*;

import javax.ws.rs.*;
import java.util.*;

@Path("/journeys")
@Consumes(value="application/xml,application/json")
@Produces(value="application/xml,application/json")
public interface RestJourneyPlanService
{
  @GET
  List<Journey> getJourneys();
  @GET
  @Path("/{journeyId}")
  Journey getJourney(@PathParam("journeyId") int journeyId);
  @PUT
  void updateJourney(Journey journey);
  @POST
  Journey createJourney (Journey journey);
  @DELETE
  @Path("/{journeyId}")
  void cancelJourney(@PathParam("journeyId") int journeyId);
}
