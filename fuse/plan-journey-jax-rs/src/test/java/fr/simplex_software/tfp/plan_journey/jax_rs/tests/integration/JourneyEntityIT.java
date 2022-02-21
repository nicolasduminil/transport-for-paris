package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import com.github.database.rider.core.util.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JourneyEntityIT extends TestCommons
{
  private EntityManager em;
  private static Map<String, Object> entityManagerProviderProperties = new HashMap<>();
  private static JourneyEntity journeyEntity;

  @BeforeClass
  public static void beforeClass()
  {
    journeyEntity = (JourneyEntity) unmarshalXmlFileToJourneyEntity(new File("src/test/resources/journey.xml"));
  }

  @Rule
  public EntityManagerProvider entityManagerProvider = EntityManagerProvider.instance("paris-oracle-test-rest");

  @Before
  public void setUp()
  {
    em = entityManagerProvider.getEm();
  }

  @Test
  public void test0()
  {
    assertNotNull(em);
  }

  @Test
  public void test1()
  {
    assertNotNull(journeyEntity);
    assertNotNull(journeyEntity.getResult());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult().getJourney());
    assertEquals(0, journeyEntity.getResult().getId().intValue());
    assertNotNull(journeyEntity.getResult().getDestinations());
    assertNotNull(journeyEntity.getMetadata().getJourney());
    JourneyDto journeyDto = new JourneyDto(journeyEntity);
    journeyEntity = new JourneyEntity(journeyDto);
    assertNotNull(journeyEntity);
    assertNotNull(journeyEntity.getResult());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult().getJourney());
    assertNotNull(journeyEntity.getResult().getDestinations());
    assertNotNull(journeyEntity.getMetadata().getJourney());
  }

  @Test
  public void test2()
  {
    em.getTransaction().begin();
    em.persist(journeyEntity);
    em.getTransaction().commit();
  }

  @Test
  public void test3()
  {
    List<JourneyEntity> journeyEntityList = em.createQuery("select je from JourneyEntity je").getResultList();
    assertNotNull(journeyEntityList);
    assertEquals(1, journeyEntityList.size());
    assertNotNull(journeyEntityList.get(0));
    assertEquals("MyJourney", journeyEntityList.get(0).getName());
  }

  @Test
  public void test4()
  {
    em.getTransaction().begin();
    JourneyEntity journeyEntity = (JourneyEntity) em.createQuery("select je from JourneyEntity je where je.name = 'MyJourney'").getSingleResult();
    assertNotNull(journeyEntity);
    journeyEntity.setName("MyJourney100");
    em.merge(journeyEntity);
    em.getTransaction().commit();
  }

  @Test
  public void test5()
  {
    em.getTransaction().begin();
    JourneyEntity journeyEntity = (JourneyEntity) em.createQuery("select je from JourneyEntity je where je.name = 'MyJourney100'").getSingleResult();
    assertNotNull(journeyEntity);
    em.remove(journeyEntity);
    em.getTransaction().commit();
    assertEquals(0, em.createQuery("select je from JourneyEntity je").getResultList().size());
  }
}