package fr.simplex_software.tfp.plan_journey.model.dtos;

public enum TransportType
{
  SUBWAY("metros"), BUS("buses"), TRAMWAY("tramways"), SUB_URBAN_TRAIN("rers");
  private final String transportTypeName;

  TransportType(String transportTypeName)
  {
    this.transportTypeName = transportTypeName;
  }

  public String getTransportTypeName()
  {
    return transportTypeName;
  }
}
