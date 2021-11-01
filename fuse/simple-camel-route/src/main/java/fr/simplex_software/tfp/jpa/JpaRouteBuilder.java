package fr.simplex_software.tfp.jpa;

import fr.simplex_software.tfp.jpa.model.*;
import org.apache.camel.builder.*;
import org.apache.camel.cdi.*;

import javax.enterprise.context.*;

@ApplicationScoped
@ContextName("camel-jpa-context")
public class JpaRouteBuilder extends RouteBuilder
{
  public void configure() throws Exception
  {
    from("timer:new-order?delay=0s&period=10s")
      .bean("orderService", "generateOrder")
      .toF("jpa:%s", Order.class.getName())
      .log("Inserted new order ${body.id}");
    fromF("jpa:%s?consumeDelete=false&transacted=true&joinTransaction=true&namedQuery=pendingOrders", Order.class.getName())
      .process(exchange ->
      {
        Order order = exchange.getIn().getBody(Order.class);
        order.setStatus("PROCESSED");
      })
      .toF("jpa:%s", Order.class.getName())
      .log("Processed order #id ${body.id} with ${body.amount} copies of the «${body.description}» book");
  }
}
