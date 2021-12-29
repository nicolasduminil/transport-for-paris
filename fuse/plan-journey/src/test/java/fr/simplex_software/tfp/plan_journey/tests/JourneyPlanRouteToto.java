package fr.simplex_software.tfp.plan_journey.tests;

import fr.simplex_software.tfp.plan_journey.model.*;
import fr.simplex_software.tfp.plan_journey.provider.*;
import fr.simplex_software.tfp.plan_journey.routes.*;
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.component.properties.*;
import org.apache.camel.impl.*;
import org.apache.camel.test.junit4.*;
import org.junit.*;

import java.util.*;

public class JourneyPlanRouteToto extends CamelTestSupport
{
  private JourneyPlanService journeyPlanService = new JourneyPlanService();

  @Override
  protected CamelContext createCamelContext() throws Exception
  {
    CamelContext camelContext = super.createCamelContext();
    ((org.apache.camel.impl.DefaultCamelContext) camelContext).setRegistry(createRegistry());
    PropertiesComponent prop = camelContext.getComponent("properties", PropertiesComponent.class);
    prop.setLocation("classpath:application.properties");
    return camelContext;
  }

  @Override
  protected JndiRegistry createRegistry() throws Exception
  {
    JndiRegistry jndi = super.createRegistry();
    jndi.bind("journeyPlanService", journeyPlanService);
    return jndi;
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception
  {
    return new JourneyPlanRoute();
  }


  @Test
  public void testGetOrder() throws Exception
  {
    List<Journey> journeys = template.requestBodyAndHeader("restlet:http://localhost:8080/plan-journey/tfp/journeys?restletMethod=GET", null, Exchange.CONTENT_TYPE, "application/xml", List.class);
    assertNotNull(journeys);
    assertEquals(0, journeys.size());
  }
}
