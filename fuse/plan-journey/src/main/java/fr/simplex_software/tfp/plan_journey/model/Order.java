package fr.simplex_software.tfp.plan_journey.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order
{
  @XmlAttribute
  private int id;
  @XmlAttribute
  private String partName;
  @XmlAttribute
  private int amount;
  @XmlAttribute
  private String customerName;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getPartName()
  {
    return partName;
  }

  public void setPartName(String partName)
  {
    this.partName = partName;
  }

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public void setCustomerName(String customerName)
  {
    this.customerName = customerName;
  }
}
