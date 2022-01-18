package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import com.github.database.rider.core.*;
import com.github.database.rider.core.api.dataset.*;
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
public class JourneyEntity1IT extends TestCommons
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
  public EntityManagerProvider entityManagerProvider = EntityManagerProvider.instance("paris-oracle");

  @Rule
  public DBUnitRule dbUnitRule = DBUnitRule.instance(entityManagerProvider.connection());

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
  @DataSet(cleanBefore = true, cleanAfter = true)
  @ExpectedDataSet(value = "datasets/j1.xml", ignoreCols = {"METADATA_WHEN", "RESULT_RESULT_ID"})
  public void test2()
  {
    em.getTransaction().begin();
    em.persist(journeyEntity);
    em.getTransaction().commit();
  }

  @Test
  @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/j2.xml", strategy = SeedStrategy.CLEAN_INSERT)
  @ExpectedDataSet(value = "datasets/j1.xml", ignoreCols = {"METADATA_WHEN", "RESULT_RESULT_ID"})
  public void test3()
  {
    em.getTransaction().begin();
    JourneyEntity je = em.getReference(JourneyEntity.class, 2L);
    em.remove(je);
    em.getTransaction().commit();
  }

  @Test
  @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/j2.xml", strategy = SeedStrategy.CLEAN_INSERT)
  @ExpectedDataSet(value = "datasets/j3.xml")
  public void test4()
  {
    em.getTransaction().begin();
    JourneyEntity je = em.getReference(JourneyEntity.class, 2L);
    je.setName("MyJourney100");
    em.merge(je);
    em.getTransaction().commit();
  }

  @Test
  @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/j2.xml", strategy = SeedStrategy.UPDATE)
  public void test5()
  {
    assertEquals(2, em.createQuery("select je from JourneyEntity je").getResultList().size());
  }
}