package fr.simplex_software.tfp.jpa.service;

import fr.simplex_software.tfp.jpa.model.*;
import info.schnatterer.mobynamesgenerator.*;

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
    order.setItem(MobyNamesGenerator.getRandomName());
    order.setAmount(amount.nextInt(10) + 1);
    order.setDescription(MobyNamesGenerator.getRandomName());
    return order;
  }
}
