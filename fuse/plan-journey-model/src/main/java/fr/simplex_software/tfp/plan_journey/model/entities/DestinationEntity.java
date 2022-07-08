package fr.simplex_software.tfp.plan_journey.model.entities;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import java.io.*;

@Entity
@Table(name = "DESTINATIONS")
@XmlRootElement(name="destination")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DestinationEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "DESTINATIONS_ID_GENERATOR", sequenceName = "DESTINATIONS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESTINATIONS_ID_GENERATOR")
  @Column(name = "DESTINATION_ID", unique = true, nullable = false, length = 8)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private ResultEntity result;
  @NotEmpty
  @Column(name = "STATION_NAME", nullable = false, length = 80)
  private String stationName;
  @NotEmpty
  @Column(name = "PLATFORM_ID", nullable = false, length = 40)
  private String platformId;

  public DestinationEntity (String stationName, String platformId)
  {
    this.platformId = platformId;
    this.stationName = stationName;
  }


  public DestinationEntity (DestinationDto destinationDto)
  {
    this.stationName = destinationDto.getStationName();
    this.platformId = destinationDto.getPlatformId();
  }
}
