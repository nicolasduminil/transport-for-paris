package fr.simplex_software.tfp.plan_journey.model.entities;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "RESULTS")
@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
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

  public ResultEntity()
  {
  }

  public ResultEntity(List<DestinationEntity> destinations)
  {
    this.destinations = destinations;
  }

  public ResultEntity (ResultDto resultDto)
  {
    this (new ArrayList<>());
    resultDto.getDestinations().forEach(d -> this.getDestinations().add(new DestinationEntity(d)));
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public List<DestinationEntity> getDestinations()
  {
    return destinations;
  }

  public void setDestinations(List<DestinationEntity> destinations)
  {
    this.destinations = destinations;
  }

  public JourneyEntity getJourney()
  {
    return journey;
  }

  public void setJourney(JourneyEntity journey)
  {
    this.journey = journey;
  }

  public void addDestination (DestinationEntity destinationEntity)
  {
    destinations.add(destinationEntity);
  }

  public void removeDestination (DestinationEntity destinationEntity)
  {
    destinations.remove(destinationEntity);
  }
}
