package fr.simplex_software.tfp.plan_journey.jax_rs;

import org.apache.deltaspike.jpa.api.entitymanager.*;
import org.apache.deltaspike.jpa.api.transaction.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

@ApplicationScoped
//@Alternative
public class EntityManagerProducerNoEE implements EntityManagerProducer
{
  @Inject
  @PersistenceUnitName("paris-oracle")
  private EntityManagerFactory entityManagerFactory;

  @Override
  @Produces
  @TransactionScoped
  public EntityManager createEntityManager()
  {
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
