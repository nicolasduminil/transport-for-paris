package fr.simplex_software.tfp.plan_journey.jax_rs.api;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.*;

import javax.inject.*;
import javax.json.bind.*;
import javax.validation.*;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.net.*;
import java.util.*;

@Path("/journeys")
@Consumes(value = "application/json,application/xml")
@Produces(value = "application/json,application/xml")
@Api(value = "Paris Data API", description = "Operations pertaining on the Paris Data API")
@Slf4j
public class JaxRsJourneyPlanService
{
  private static final String pierreGrimaudRatpApiUrl = "https://api-ratp.pierre-grimaud.fr/v4";
  private static WebTarget webTarget = ClientBuilder.newClient().target(pierreGrimaudRatpApiUrl);
  @Inject
  private PlanJourneyService planJourneyService;

  @GET
  @ApiOperation(value = "getJourneys", notes = "Get all journeys")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list", response = List.class), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  public Response getJourneys()
  {
    GenericEntity<List<JourneyDto>> journeys = new GenericEntity<>(planJourneyService.getJourneys()){};
    return Response.ok().entity(journeys).build();
  }

  @GET
  @Path("/{journeyId}")
  @ApiOperation(value = "getJourney", notes = "Get a journey by its ID")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved journey", response = JourneyDto.class), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  public Response getJourney(@PathParam("journeyId") Long journeyId)
  {
    return Response.ok().entity(planJourneyService.getJourney(journeyId)).build();
  }

  @GET
  @Path("id/{journeyName}")
  @ApiOperation(value = "getJourneyIdByName", notes = "Get a journey ID by its name")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved jpurney ID", response = String.class), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  public Response getJourneyIdByName (@PathParam("journeyName") final String name)
  {
    return Response.ok(Long.toString(planJourneyService.getJourneyIdByName(name))).build();
  }

  @GET
  @Path("ref/{journeyName}")
  @ApiOperation(value = "getJourneyByName", notes = "Get a journey ID by its name")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved journey", response = JourneyDto.class), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  public Response getJourneyByName(@PathParam("journeyName") String journeyName)
  {
    return Response.ok().entity(planJourneyService.findByName(journeyName)).build();
  }

  @PUT
  @ApiOperation(value = "updateJourney", notes = "Update a journey")
  @ApiResponses(value = {@ApiResponse(code = 204, message = "Successfully updated journey"), @ApiResponse(code = 500, message = "Server internal erro, see the log file")})
  public void updateJourney(JourneyDto journey)
  {
    planJourneyService.updateJourney(journey);
  }

  @POST
  @ApiOperation(value = "createJourney", notes = "Create a journey")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created journey", response = JourneyDto.class), @ApiResponse(code = 500, message = "Server internal erro, see the log file")})
  public Response createJourney(JourneyDto journeyDto)
  {
    System.out.println ("### JaxRsJourneyPlanService.cancelJourney(): Creating new journey");
    return Response.created(URI.create("/journeys/")).entity(planJourneyService.createJourney(journeyDto)).build();
  }

  @DELETE
  @Path("/{journeyId}")
  @ApiOperation(value = "cancelJourney", notes = "Remove a journey by its ID")
  @ApiResponses(value = {@ApiResponse(code = 204, message = "Successfully removed journey"), @ApiResponse(code = 500, message = "Server internal erro, see the log file")})
  public void cancelJourney(@PathParam("journeyId") Long journeyId)
  {
    log.error("### JaxRsJourneyPlanService.cancelJourney(): Removing Journey with ID {}", journeyId);
    System.out.println ("### JaxRsJourneyPlanService.cancelJourney(): Removing Journey with ID " + journeyId);
    planJourneyService.removeJourney(planJourneyService.getJourney(journeyId));
  }

  @GET
  @Path("/destinations/{type}/{line}")
  @ApiOperation(value = "Returns the list of all the destinations for a given transport type and a line ID", response = DestinationDto.class, responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list", response = List.class), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
  public Response getAllDestinationsByTypeAndLine(@ApiParam(value = "The transport type", required = true) @Valid @PathParam(value = "type") TransportType transportType, @ApiParam(value = "The line ID", required = true) @Valid @PathParam(value = "line") String lineId)
  {
    String path = String.format ("/destinations/%s/%s", transportType.getTransportTypeName(), lineId);
    return webTarget.path(path).request().get();
  }

  @POST
  @Path("/new")
  @ApiOperation(value = "Create a journey using journey's params", response = DestinationDto.class, responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list", response = JourneyDto.class), @ApiResponse(code = 500, message = "Server internal erro, see the log file")})
  public Response createJourney (JourneyParams journeyParams)
  {
    String json = getAllDestinationsByTypeAndLine(journeyParams.getTransportType(), journeyParams.getLineId()).readEntity(String.class);
    return Response.created(URI.create("/journeys/")).entity(planJourneyService.createJourney(new JourneyDto(JsonbBuilder.create().fromJson(json, ResponseDto.class)))).build();
  }

  public void setWebTarget (WebTarget webTarget)
  {
    JaxRsJourneyPlanService.webTarget = webTarget;
  }
}
