package fr.simplex_software.tfp.plan_journey.repository;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import org.apache.deltaspike.data.api.*;

import java.util.*;

@Repository
public interface PlanJourneyRepository extends EntityRepository<JourneyEntity, Long>, EntityManagerDelegate<JourneyEntity>
{
  Optional<JourneyEntity> findByName (String name);
}
