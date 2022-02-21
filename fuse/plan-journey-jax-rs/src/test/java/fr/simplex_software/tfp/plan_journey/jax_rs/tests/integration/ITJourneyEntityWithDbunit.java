package fr.simplex_software.tfp.plan_journey.jax_rs.tests.integration;

import com.github.database.rider.core.*;
import com.github.database.rider.core.api.dataset.*;
import com.github.database.rider.core.util.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.junit.*;

import javax.persistence.*;
import java.util.*;

import static org.junit.Assert.*;

public class ITJourneyEntityWithDbunit extends TestCommons
{
  private EntityManager em;
  private static Map<String, Object> entityManagerProviderProperties = new HashMap<>();
  private static JourneyEntity journeyEntity;

  @BeforeClass
  public static void beforeClass()
  {
    journeyEntity = getJourneyEntity();
  }

  @Rule
  public EntityManagerProvider entityManagerProvider = EntityManagerProvider.instance("paris-oracle-test-rest");

  @Rule
  public DBUnitRule dbUnitRule = DBUnitRule.instance(entityManagerProvider.connection());

  @Before
  public void setUp()
  {
    em = entityManagerProvider.getEm();
  }

  @Test
  @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/j1.xml", strategy = SeedStrategy.CLEAN_INSERT)
  @ExpectedDataSet(value = "datasets/j2.xml", ignoreCols = "JOURNEY_DATE")
  public void test2()
  {

    em.getTransaction().begin();
    em.persist(journeyEntity);
    em.getTransaction().commit();
    List<JourneyEntity> journeyEntityList = em.createQuery("select je from JourneyEntity je").getResultList();
  }

  @Test
  @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/j1.xml", strategy = SeedStrategy.CLEAN_INSERT)
  public void test3()
  {
    em.getTransaction().begin();
    List<JourneyEntity> journeyEntityList = em.createQuery("select je from JourneyEntity je").getResultList();
    assertNotNull(journeyEntityList);
    assertEquals(1, journeyEntityList.size());
    assertNotNull(journeyEntityList.get(0));
    assertEquals("MyJourney", journeyEntityList.get(0).getName());
    em.remove(journeyEntityList.get(0));
    assertEquals(0, em.createQuery("select je from JourneyEntity je").getResultList().size());
    em.getTransaction().commit();
  }
}
