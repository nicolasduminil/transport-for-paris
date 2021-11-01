package fr.simplex_software.tfp.jpa.util;

import javax.enterprise.inject.*;
import javax.persistence.*;

public class Resources
{
  @Produces
  @PersistenceContext
  private EntityManager em;
}
