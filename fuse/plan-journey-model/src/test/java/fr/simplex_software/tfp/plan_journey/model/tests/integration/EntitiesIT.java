package fr.simplex_software.tfp.plan_journey.model.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.*;
import org.junit.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class EntitiesIT extends JpaHibernateTest
{
  @Test
  public void testJourneyEntity()
  {
    JourneyEntity journeyEntity = (JourneyEntity)unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
    assertNotNull(journeyEntity);
    journeyEntity = new JourneyEntity(new JourneyDto(journeyEntity));
    getEm().getTransaction().begin();
    getEm().persist(journeyEntity);
    getEm().getTransaction().commit();
    Query q = getEm().createQuery("select je from JourneyEntity je");
    List<JourneyEntity> jes = q.getResultList();
    assertNotNull(jes);
    assertEquals(1, jes.size());
    getEm().getTransaction().begin();
    getEm().remove(journeyEntity);
    getEm().getTransaction().commit();
  }
}
