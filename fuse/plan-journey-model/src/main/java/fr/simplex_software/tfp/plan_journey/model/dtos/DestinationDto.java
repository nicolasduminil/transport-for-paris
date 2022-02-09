package fr.simplex_software.tfp.plan_journey.model.dtos;

import fr.simplex_software.tfp.plan_journey.model.entities.*;

import javax.json.bind.annotation.*;
import javax.xml.bind.annotation.*;
import java.io.*;

@XmlRootElement(name="destination")
@XmlAccessorType(XmlAccessType.FIELD)
public class DestinationDto implements Serializable
{
  @JsonbProperty("name")
  private String stationName;
  @JsonbProperty("way")
  private String platformId;

  public DestinationDto()
  {
  }

  public DestinationDto(String stationName, String platformId)
  {
    this.stationName = stationName;
    this.platformId = platformId;
  }

  public DestinationDto (DestinationEntity destinationEntity)
  {
    this.platformId = destinationEntity.getPlatformId();
    this.stationName = destinationEntity.getStationName();
  }

  public String getStationName()
  {
    return stationName;
  }

  public void setStationName(String stationName)
  {
    this.stationName = stationName;
  }

  public String getPlatformId()
  {
    return platformId;
  }

  public void setPlatformId(String platformId)
  {
    this.platformId = platformId;
  }
}
