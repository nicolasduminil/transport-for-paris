package fr.simplex_software.tfp.plan_journey.api;

import fr.simplex_software.tfp.plan_journey.model.*;

import javax.ws.rs.*;

@Path("/orders/")
@Consumes(value = "application/xml,application/json")
@Produces(value = "application/xml,application/json")
public interface RestOrderService
{
  @GET
  @Path("/{id}")
  public Order getOrder(@PathParam("id") int orderId);
  @PUT
  void updateOrder(Order order);
  @POST
  String createOrder(Order order);
  @DELETE
  @Path("/{id}")
  void cancelOrder(@PathParam("id") int orderId);}
