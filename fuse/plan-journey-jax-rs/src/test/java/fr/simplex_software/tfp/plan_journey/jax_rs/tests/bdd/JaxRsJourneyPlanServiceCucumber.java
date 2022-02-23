package fr.simplex_software.tfp.plan_journey.jax_rs.tests.bdd;

import io.cucumber.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/it/features")
public class JaxRsJourneyPlanServiceCucumber
{
}
