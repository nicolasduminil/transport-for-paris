package fr.simplex_software.tfp.plan_journey.provider;

import fr.simplex_software.tfp.plan_journey.model.*;

import javax.enterprise.context.*;
import javax.inject.*;
import java.util.*;
import java.util.concurrent.atomic.*;

@ApplicationScoped
@Named("orderService")
public class DummyOrderService implements OrderService
{
  private Map<Integer, Order> orders = new HashMap<>();

  private final AtomicInteger idGen = new AtomicInteger();

  public DummyOrderService() {
    // setup some dummy orders to start with
    setupDummyOrders();
  }

  @Override
  public Order getOrder(int orderId) {
    return orders.get(orderId);
  }

  @Override
  public void updateOrder(Order order) {
    int id = order.getId();
    orders.remove(id);
    orders.put(id, order);
  }

  @Override
  public String createOrder(Order order) {
    int id = idGen.incrementAndGet();
    order.setId(id);
    orders.put(id, order);
    return "" + id;
  }

  @Override
  public void cancelOrder(int orderId) {
    orders.remove(orderId);
  }

  public void setupDummyOrders() {
    Order order = new Order();
    order.setAmount(1);
    order.setPartName("motor");
    order.setCustomerName("honda");
    createOrder(order);

    order = new Order();
    order.setAmount(3);
    order.setPartName("brake");
    order.setCustomerName("toyota");
    createOrder(order);
  }
}
