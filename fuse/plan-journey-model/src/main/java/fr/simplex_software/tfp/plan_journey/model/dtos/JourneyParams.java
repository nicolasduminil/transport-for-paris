package fr.simplex_software.tfp.plan_journey.model.dtos;

import lombok.*;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement(name = "params")
@XmlAccessorType(XmlAccessType.FIELD)
public class JourneyParams
{
  private TransportType transportType;
  private String lineId;
}
