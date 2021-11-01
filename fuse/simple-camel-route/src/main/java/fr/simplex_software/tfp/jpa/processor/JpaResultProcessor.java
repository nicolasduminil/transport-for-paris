package fr.simplex_software.tfp.jpa.processor;

import fr.simplex_software.tfp.jpa.model.*;
import org.apache.camel.*;

import javax.inject.*;
import java.util.*;

@Named
public class JpaResultProcessor implements Processor
{
  @SuppressWarnings("unchecked")
  @Override
  public void process(Exchange exchange)
  {
    List<Order> orders = exchange.getIn().getBody(List.class);
    if (orders != null && !orders.isEmpty())
      exchange.getOut().setBody(orders.get(0));
  }
}
