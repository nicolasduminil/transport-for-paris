package fr.simplex_software.tfp.jpa;

import fr.simplex_software.tfp.jpa.model.*;
import fr.simplex_software.tfp.jpa.service.*;
import org.apache.camel.builder.*;
import org.apache.camel.cdi.*;

import javax.enterprise.context.*;
import javax.inject.*;

@ApplicationScoped
@ContextName("camel-jpa-context")
public class JpaRouteBuilder extends RouteBuilder
{
  public void configure()
  {
    from("timer:new-order?delay=0&period=10000")
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
      .log("Processed order #id ${body.id} with ${body.amount} amount of the «${body.description}» item");
  }
}
