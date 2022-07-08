package fr.simplex_software.tfp.plan_journey.model.entities;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.*;

@Entity
@Table(name = "JOURNEYS")
@XmlRootElement(name = "journey")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JourneyEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "JOURNEYS_ID_GENERATOR", sequenceName = "JOURNEYS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOURNEYS_ID_GENERATOR")
  @Column(name = "JOURNEY_ID", unique = true, nullable = false, length = 8)
  private Long id;
  @Column(name = "JOURNEY_NAME", unique = true, nullable = false, length = 80)
  private String name;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "journey", optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
  private ResultEntity result;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "journey", optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
  private MetadataEntity metadata;

  public JourneyEntity(String name, ResultEntity result, MetadataEntity metadata)
  {
    this.name = name;
    this.result = result;
    this.metadata = metadata;
  }

  public JourneyEntity (JourneyDto journeyDto)
  {
    this (journeyDto.getName(), new ResultEntity(journeyDto.getResult()), new MetadataEntity(journeyDto.getMetadata()));
    this.result.getJourney().setResult(this.getResult());
  }

  public JourneyEntity fromDto (JourneyDto journeyDto)
  {
    this.setMetadata(new MetadataEntity(journeyDto.getMetadata()));
    this.getMetadata().setJourney(this);
    this.setResult(new ResultEntity(journeyDto.getResult()));
    this.getResult().setJourney(this);
    return this;
  }
}
