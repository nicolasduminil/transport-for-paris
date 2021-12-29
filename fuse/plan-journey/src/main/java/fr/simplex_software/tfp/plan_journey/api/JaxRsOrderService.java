package fr.simplex_software.tfp.plan_journey.api;

import fr.simplex_software.tfp.plan_journey.model.*;
import fr.simplex_software.tfp.plan_journey.provider.*;

import javax.inject.*;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/orders2/")
@Produces(value="application/xml,application/json")
public class JaxRsOrderService
{
  @Inject
  private OrderService orderService;

  public void setOrderService(OrderService orderService)
  {
    this.orderService = orderService;
  }

  @GET
  @Path("/{id}")
  public Response getOrder(@PathParam("id") int orderId)
  {
    Order order = orderService.getOrder(orderId);
    if (order != null)
    {
      return Response.ok(order).build();
    } else
    {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @PUT
  public Response updateOrder(Order order)
  {
    orderService.updateOrder(order);
    return Response.ok().build();
  }

  @POST
  public Response createOrder(Order order)
  {
    String id = orderService.createOrder(order);
    return Response.ok(id).build();
  }

  @DELETE
  @Path("/{id}")
  public Response cancelOrder(@PathParam("id") int orderId)
  {
    orderService.cancelOrder(orderId);
    return Response.ok().build();
  }
}
