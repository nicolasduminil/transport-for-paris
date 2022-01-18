package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import lombok.extern.slf4j.*;
import org.testcontainers.containers.*;
import org.testcontainers.containers.output.*;
import org.testcontainers.containers.wait.strategy.*;

@Slf4j
public class TestBase extends TestCommons
{
  protected static final GenericContainer<?> oracle;
  protected static JourneyEntity journeyEntity;

  static
  {
    oracle = new GenericContainer<>("oracleinanutshell/oracle-xe-11g:latest")
      .withExposedPorts(1521, 5500)
      .withEnv ("ORACLE_ALLOW_REMOTE", "true")
      .withEnv("ORACLE_DISABLE_ASYNCH_IO", "true")
      .withClasspathResourceMapping("scripts/oracle", "/docker-entrypoint-initdb.d", BindMode.READ_WRITE)
      .withLogConsumer(new Slf4jLogConsumer(log))
      .withReuse(true)
      .waitingFor(Wait.forLogMessage(".*SQL>.*", 1));
    oracle.start();
  }
}
