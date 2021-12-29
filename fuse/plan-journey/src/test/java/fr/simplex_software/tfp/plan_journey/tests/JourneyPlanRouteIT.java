package fr.simplex_software.tfp.plan_journey.tests;

import lombok.extern.slf4j.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.arquillian.test.api.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;
import org.wildfly.camel.test.common.http.*;

import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

import static org.junit.Assert.*;

@RunAsClient
@RunWith(Arquillian.class)
public class JourneyPlanRouteIT
{
  @ArquillianResource
  private URL url;

  @Deployment
  public static WebArchive createDeployment()
  {
    return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/plan-journey.war"));
  }

  @Test
  public void testGetJourneys() throws Exception
  {
    HttpRequest.HttpResponse response = HttpRequest.get(getResourceAddress("/journeys"))
      .header("Content-Type", MediaType.APPLICATION_JSON)
      .getResponse();
    assertEquals(200, response.getStatusCode());
    assertNull(response.getBody());
  }

  private String getResourceAddress(String resourcePath) throws MalformedURLException
  {
    return "http://localhost:19990/plan-journey/tfp" + resourcePath;
  }
}
