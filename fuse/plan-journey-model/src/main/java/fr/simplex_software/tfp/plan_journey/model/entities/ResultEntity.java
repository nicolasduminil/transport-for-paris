package fr.simplex_software.tfp.plan_journey.model.entities;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "RESULTS")
@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "RESULT_ID_GENERATOR", sequenceName = "RESULTS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_ID_GENERATOR")
  @Column(name = "RESULT_ID", unique = true, nullable = false, length = 8)
  private Long id;
  @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DestinationEntity> destinations;
  @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name="JOURNEY_ID")
  private JourneyEntity journey;

  public ResultEntity(List<DestinationEntity> destinations)
  {
    this.destinations = destinations;
  }

  public ResultEntity (ResultDto resultDto)
  {
    this (new ArrayList<>());
    resultDto.getDestinations().forEach(d -> this.getDestinations().add(new DestinationEntity(d)));
  }
}
