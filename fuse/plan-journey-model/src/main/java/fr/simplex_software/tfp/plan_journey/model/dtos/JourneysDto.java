package fr.simplex_software.tfp.plan_journey.model.dtos;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "journeyDtoList")
@XmlAccessorType (XmlAccessType.FIELD)
public class JourneysDto
{
  private List<JourneyDto> journeyDtoList;

  public List<JourneyDto> getJourneyDtoList()
  {
    return journeyDtoList;
  }

  public void setJourneyDtoList(List<JourneyDto> journeyDtoList)
  {
    this.journeyDtoList = journeyDtoList;
  }
}
