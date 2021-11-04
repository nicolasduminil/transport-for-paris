package fr.simplex_software.tfp.jpa.model;

import lombok.*;

import javax.persistence.*;

@Entity(name="orders")
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@NamedQuery(name = "pendingOrders", query = "SELECT o FROM orders o WHERE o.status = 'PENDING'")
public class Order
{
  @Id
  @SequenceGenerator(name = "ORDER_ID_GENERATOR", sequenceName = "ORDER_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GENERATOR")
  @Column(name="ORDER_ID", unique = true, nullable = false, length = 5)
  private int id;
  @Column(name="ORDER_ITEM", nullable=false)
  private String item;
  @Column(name="ORDER_AMOUNT", nullable=false)
  private int amount;
  @Column(name="ORDER_DESCRIPTION", nullable=false)
  private String description;
  @Column(name="ORDER_STATUS", nullable=false)
  private String status = "PENDING";
}
