package fr.simplex_software.tfp.plan_journey.jax_rs;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

@Named("EntityManagerProducerEE")
public class EntityManagerProducerEE implements EntityManagerProducer
{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Produces
  @RequestScoped
  public EntityManager createEntityManager()
  {
    return this.entityManager;
  }

  @Override
  public void closeEntityManager(@Disposes EntityManager entityManager)
  {
    if (entityManager.isOpen())
    {
      entityManager.close();
    }
  }
}
