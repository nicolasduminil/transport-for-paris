package fr.simplex_software.tfp.plan_journey.tests;

import fr.simplex_software.tfp.plan_journey.model.*;
import org.junit.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import static org.junit.Assert.*;

public class OrderIT
{
  private static Client client;
  private static WebTarget webTarget;

  @Before
  public void setUp()
  {
    client = ClientBuilder.newClient();
    webTarget = client.target("http://localhost:18080/plan-journey/tfp/orders2");
  }

  @After
  public void tearDown()
  {
    if (client != null)
    {
      client.close();
      client = null;
    }
    webTarget = null;
  }

  @Test
  public void test1()
  {
    Response response = webTarget.path(Integer.toString(1)).request().get();
    assertEquals(200, response.getStatus());
  }
}
