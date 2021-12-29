package fr.simplex_software.tfp.plan_journey.model.dtos;

import com.fasterxml.jackson.annotation.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("result")
public class ResultDto implements Serializable
{
  private List<DestinationDto> destinations;

  public ResultDto()
  {
  }

  public ResultDto(List<DestinationDto> destinations)
  {
    this.destinations = destinations;
  }

  public ResultDto (ResultEntity resultEntity)
  {
    this.setDestinations(new ArrayList<>());
    resultEntity.getDestinations().forEach(d -> this.getDestinations().add(new DestinationDto(d)));
  }

  public List<DestinationDto> getDestinations()
  {
    return destinations;
  }

  public void setDestinations(List<DestinationDto> destinations)
  {
    this.destinations = destinations;
  }

  public void addDestination (DestinationDto destinationDto)
  {
    destinations.add(destinationDto);
  }

  public void removeDestination (DestinationDto destinationDto)
  {
    destinations.remove(destinationDto);
  }
}
