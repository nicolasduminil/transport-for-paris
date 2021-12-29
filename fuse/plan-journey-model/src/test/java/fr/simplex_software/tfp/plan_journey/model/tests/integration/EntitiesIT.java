package fr.simplex_software.tfp.plan_journey.model.tests.integration;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.*;
import org.junit.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/*@RunAsClient
@RunWith(Arquillian.class)*/
public class EntitiesIT extends JpaHibernateTest
{
  /*@DataSet(value = "datasets/customers.yml", strategy = SeedStrategy.CLEAN_INSERT)
  @ExpectedDataSet(value = "datasets/customer-create-expected.yml", ignoreCols = "CUSTOMER_ID")*/
  @Test
  public void testJourneyEntity()
  {
    JourneyEntity journeyEntity = (JourneyEntity)unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
    assertNotNull(journeyEntity);
    journeyEntity.getResult().setJourney(journeyEntity);
    journeyEntity.getMetadata().setJourney(journeyEntity);
    getEm().getTransaction().begin();
    getEm().persist(journeyEntity);
    getEm().getTransaction().commit();
    Query q = getEm().createQuery("select je from JourneyEntity je");
    List<JourneyEntity> jes = q.getResultList();
    assertNotNull(jes);
    assertEquals(1, jes.size());
  }
}
