package fr.simplex_software.tfp.plan_journey.jax_rs;

import jakarta.transaction.*;
import org.apache.deltaspike.jpa.api.entitymanager.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

@ApplicationScoped
public class EntityManagerProducerEE
{
  @Inject
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;

  @Produces
  @RequestScoped
  public EntityManager createEntityManager()
  {
    return entityManagerFactory.createEntityManager();
  }

  public void closeEntityManager(@Disposes @Default EntityManager entityManager)
  {
    if (entityManager.isOpen())
      entityManager.close();
  }
}
