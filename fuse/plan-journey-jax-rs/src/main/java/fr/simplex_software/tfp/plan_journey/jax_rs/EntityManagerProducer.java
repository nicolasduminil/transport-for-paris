package fr.simplex_software.tfp.plan_journey.jax_rs;

import javax.persistence.*;

public interface EntityManagerProducer
{
  EntityManager createEntityManager();
  void closeEntityManager (EntityManager entityManager);
}
