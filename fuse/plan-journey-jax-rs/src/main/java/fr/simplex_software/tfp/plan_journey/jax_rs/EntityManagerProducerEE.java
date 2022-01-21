package fr.simplex_software.tfp.plan_journey.jax_rs;

import org.apache.deltaspike.jpa.api.entitymanager.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

public class EntityManagerProducerEE implements EntityManagerProducer
{
  @Inject
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  /*@Produces
  @RequestScoped*/
  public EntityManager createEntityManager()
  {
    if (entityManager == null)
    {
      if (entityManagerFactory == null)
        Persistence.createEntityManagerFactory("paris-oracle");
      entityManager = entityManagerFactory.createEntityManager();
    }
    return this.entityManager;
  }

  @Override
  public void closeEntityManager(/*@Disposes @Default*/ EntityManager entityManager)
  {
    if (entityManager.isOpen())
    {
      entityManager.close();
    }
  }
}
