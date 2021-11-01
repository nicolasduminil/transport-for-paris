package fr.simplex_software.tfp.jpa.producer;

import org.springframework.transaction.*;
import org.springframework.transaction.jta.*;

import javax.enterprise.inject.*;
import javax.inject.*;
import javax.transaction.*;

public class TransactionManagerProducer
{
  @Inject
  private UserTransaction userTransaction;

  @Produces
  @Named("jtaTransactionManager")
  public PlatformTransactionManager createTransactionManager()
  {
    JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    jtaTransactionManager.setUserTransaction(userTransaction);
    jtaTransactionManager.afterPropertiesSet();
    return jtaTransactionManager;
  }
}
