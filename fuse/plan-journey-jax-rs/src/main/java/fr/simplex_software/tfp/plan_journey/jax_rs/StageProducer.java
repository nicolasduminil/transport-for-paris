package fr.simplex_software.tfp.plan_journey.jax_rs;

import org.apache.deltaspike.core.api.projectstage.*;
import org.apache.deltaspike.core.util.*;

import javax.enterprise.inject.*;
import javax.inject.*;

@Singleton
public class StageProducer
{
  @Produces
  @Named("toto")
  public ProjectStage currentProjectStage() {
    return ProjectStageProducer.getInstance().getProjectStage();
  }
}
