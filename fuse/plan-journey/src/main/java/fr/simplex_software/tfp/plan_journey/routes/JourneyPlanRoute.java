package fr.simplex_software.tfp.plan_journey.routes;

import org.apache.camel.builder.*;
import org.apache.camel.cdi.*;
import org.apache.camel.component.properties.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;

@ApplicationScoped
@ContextName("camel-rest-context1")
public class JourneyPlanRoute extends RouteBuilder
{
  @Produces
  @Named("properties")
  public PropertiesComponent propertiesComponent()
  {
    PropertiesComponent component = new PropertiesComponent();
    component.setLocation("classpath:application.properties");
    return component;
  }

  @Override
  public void configure() throws Exception
  {
    from("cxfrs:http:{{host}}:{{port}}/plan-journey/tfp/journeys?resourceClasses=fr.simplex_software.tfp.plan_journey.api.RestJourneyPlanService&bindingStyle=SimpleConsumer")
      .toD("direct:${header.operationName}");
    from("direct:getJourneys").bean("journeyPlanService", "getJourneys");
    from("direct:getJourney").bean("journeyPlanService", "getJourney($header.id})");
    from("direct:updateJourney").bean("journeyPlanService", "updateJourney");
    from("direct:createJourneys").bean("journeyPlanService", "createJourney");
    from("direct:cancelJourney").bean("journeyPlanService", "cancelJourney($header.id})");
  }
}
