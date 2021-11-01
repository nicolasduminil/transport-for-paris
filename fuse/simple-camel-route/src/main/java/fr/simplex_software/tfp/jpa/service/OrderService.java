package fr.simplex_software.tfp.jpa.service;

import fr.simplex_software.tfp.jpa.model.*;

import javax.inject.*;
import java.util.*;
import java.util.concurrent.atomic.*;

@Named
@Singleton
public class OrderService
{
  private final AtomicInteger counter = new AtomicInteger();
  private final Random amount = new Random();

  public Order generateOrder()
  {
    Order order = new Order();
    order.setItem(counter.incrementAndGet() % 2 == 0 ? "Camel" : "ActiveMQ");
    order.setAmount(amount.nextInt(10) + 1);
    order.setDescription(counter.get() % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
    return order;
  }
}
