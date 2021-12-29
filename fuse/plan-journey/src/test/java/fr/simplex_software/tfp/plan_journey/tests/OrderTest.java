package fr.simplex_software.tfp.plan_journey.tests;

import fr.simplex_software.tfp.plan_journey.model.*;
import fr.simplex_software.tfp.plan_journey.routes.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.test.junit4.*;
import org.junit.*;

public class OrderTest extends CamelTestSupport
{
  @Override
  protected RouteBuilder createRouteBuilder() throws Exception
  {
    return new OrderRoute();
  }

  @Test
  public void testGetOrder() throws Exception
  {
    String response = template.requestBodyAndHeader("restlet:http://localhost:8080/orders/{id}?restletMethod=GET", null, "id", "1", String.class);
    log.info("Response: {}", response);
  }

  /*@Test
  public void testCreateOrder() throws Exception
  {
    Order order = new Order();
    order.setAmount(1);
    order.setPartName("motor");
    order.setCustomerName("honda");
    String xml = context.getTypeConverter().convertTo(String.class, order);
    log.info("Sending order using xml payload: {}", xml);
    String id = template.requestBodyAndHeader("restlet:http://localhost:8080/orders?restletMethod=POST", xml, Exchange.CONTENT_TYPE, "application/xml", String.class);
    assertNotNull(id);
    log.info("Created new order with id " + id);
    assertEquals("1", id);
  }

  @Test
  public void testCreateAndGetOrder() throws Exception
  {
    Order order = new Order();
    order.setAmount(1);
    order.setPartName("motor");
    order.setCustomerName("honda");
    String xml = context.getTypeConverter().convertTo(String.class, order);
    log.info("Sending order using xml payload: {}", xml);
    String id = template.requestBodyAndHeader("restlet:http://localhost:8080/orders?restletMethod=POST", xml, Exchange.CONTENT_TYPE, "application/xml", String.class);
    assertNotNull(id);
    log.info("Created new order with id " + id);
    assertEquals("1", id);
    String response = template.requestBodyAndHeader("restlet:http://localhost:8080/orders/{id}?restletMethod=GET", null, "id", "1", String.class);
    log.info("Response: {}", response);
  }*/
}
