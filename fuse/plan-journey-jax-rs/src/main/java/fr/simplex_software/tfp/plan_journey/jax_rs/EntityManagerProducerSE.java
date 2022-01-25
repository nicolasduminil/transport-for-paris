package fr.simplex_software.tfp.plan_journey.jax_rs;

import org.apache.deltaspike.core.api.exclude.*;
import org.apache.deltaspike.core.api.projectstage.*;
import org.apache.deltaspike.jpa.api.entitymanager.*;
import org.apache.deltaspike.jpa.api.transaction.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

@ApplicationScoped
@Exclude(exceptIfProjectStage = ProjectStage.UnitTest.class)
public class EntityManagerProducerSE implements EntityManagerProducer
{
  @Inject
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;
  @Inject
  @Named("toto")
  private ProjectStage projectStage;


  @Override
  @Produces
  @TransactionScoped
  public EntityManager createEntityManager()
  {
    System.out.println("### EntityManagerProducerSE.createEntityManager(): " + projectStage);
    return this.entityManagerFactory.createEntityManager();
  }

  @Override
  public void closeEntityManager(@Disposes @Default EntityManager entityManager)
  {
    if (entityManager.isOpen()) {
      entityManager.close();
    }
  }
}
