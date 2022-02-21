package fr.simplex_software.tfp.plan_journey.model.tests;

import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.junit.*;

import javax.persistence.*;
import java.io.*;
import java.sql.*;

public class JpaHibernateTest extends TestCommons
{
  private static EntityManagerFactory emf;
  private static EntityManager em;

  @BeforeClass
  public static void init()
  {
    emf = Persistence.createEntityManagerFactory("paris-test");
    em = emf.createEntityManager();
  }

  @AfterClass
  public static void tearDown()
  {
    em.clear();
    em.close();
    emf.close();
  }

  public static EntityManagerFactory getEmf()
  {
    return emf;
  }

  public static EntityManager getEm()
  {
    return em;
  }
}
