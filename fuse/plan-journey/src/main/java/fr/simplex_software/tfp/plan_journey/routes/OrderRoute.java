package fr.simplex_software.tfp.plan_journey.routes;

import fr.simplex_software.tfp.plan_journey.provider.*;
import org.apache.camel.builder.*;
import org.apache.camel.cdi.*;

import javax.enterprise.context.*;
import javax.inject.*;

@ApplicationScoped
@ContextName("camel-rest-context3")
public class OrderRoute extends RouteBuilder
{
  @Inject
  private DummyOrderService orderService;

  @Override
  public void configure() throws Exception
  {
    from("cxfrs:http://localhost:8080?resourceClasses=fr.simplex_software.tfp.plan_journey.api.RestOrderService&bindingStyle=SimpleConsumer&providers=#jsonProvider")
      .toD("direct:${header.operationName}");
    from("direct:createOrder").bean(orderService, "createOrder");
      //.bean("orderService", "createOrder");
    from("direct:getOrder")
      .bean("orderService", "getOrder(${header.id})");
    from("direct:updateOrder")
      .bean("orderService", "updateOrder");
    from("direct:cancelOrder")
      .bean("orderService", "cancelOrder(${header.id})");
  }
}
