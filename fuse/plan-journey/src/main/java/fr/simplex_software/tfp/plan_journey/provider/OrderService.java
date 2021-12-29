package fr.simplex_software.tfp.plan_journey.provider;

import fr.simplex_software.tfp.plan_journey.model.*;

public interface OrderService
{
  Order getOrder(int orderId);
  void updateOrder(Order order);
  String createOrder(Order order);
  void cancelOrder(int orderId);
}
