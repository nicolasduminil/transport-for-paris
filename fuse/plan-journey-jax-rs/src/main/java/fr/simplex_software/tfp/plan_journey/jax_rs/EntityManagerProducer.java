package fr.simplex_software.tfp.plan_journey.jax_rs;

import org.apache.deltaspike.jpa.api.entitymanager.*;
import org.apache.deltaspike.jpa.api.transaction.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

@ApplicationScoped
public class EntityManagerProducer
{
  @Inject
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;

  @Produces
  @TransactionScoped
  public EntityManager create()
  {
    return this.entityManagerFactory.createEntityManager();
  }

  public void dispose(@Disposes EntityManager entityManager)
  {
    if (entityManager.isOpen())
      entityManager.close();
  }
}
