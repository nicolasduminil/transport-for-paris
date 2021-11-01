package fr.simplex_software.tfp.jpa.producer;

import org.apache.camel.component.jpa.*;
import org.springframework.transaction.*;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

public class JPAComponentProducer
{
  @Produces
  @ApplicationScoped
  @Named("jpa")
  public JpaComponent jpaComponent(PlatformTransactionManager transactionManager, EntityManager entityManager)
  {
    JpaComponent component = new JpaComponent();
    component.setTransactionManager(transactionManager);
    component.setEntityManagerFactory(entityManager.getEntityManagerFactory());
    return component;
  }
}
