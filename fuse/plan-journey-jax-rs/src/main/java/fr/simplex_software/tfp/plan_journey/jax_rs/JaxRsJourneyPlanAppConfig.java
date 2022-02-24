package fr.simplex_software.tfp.plan_journey.jax_rs;

import io.swagger.jaxrs.config.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@ApplicationPath("/tfp")
public class JaxRsJourneyPlanAppConfig extends Application
{
  public JaxRsJourneyPlanAppConfig()
  {
    init();
  }

  private void init()
  {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion("1.0.0");
    beanConfig.setSchemes(new String[]{"http"});
    beanConfig.setHost("localhost:18080");
    beanConfig.setBasePath("/plan-journey-jax-rs/tfp");
    beanConfig.setResourcePackage(this.getClass().getPackage().getName());
    beanConfig.setTitle("Paris Data API");
    beanConfig.setDescription("Operations pertaining on the Paris Data API");
    beanConfig.setScan(true);
  }
}
