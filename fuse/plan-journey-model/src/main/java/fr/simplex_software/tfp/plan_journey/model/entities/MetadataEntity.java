package fr.simplex_software.tfp.plan_journey.model.entities;

import fr.simplex_software.tfp.plan_journey.model.converters.*;
import fr.simplex_software.tfp.plan_journey.model.dtos.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import java.io.*;
import java.time.*;

@Entity
@Table(name = "METADATA")
@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class MetadataEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "METADATA_ID_GENERATOR", sequenceName = "METADATA_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METADATA_ID_GENERATOR")
  @Column(name = "METADATA_ID", unique = true, nullable = false, length = 8)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name="JOURNEY_ID")
  private JourneyEntity journey;
  @NotEmpty
  @Column(name = "METADATA_CALL", nullable = false, length = 40)
  private String metadataCall;
  @NotNull
  @Column(name = "JOURNEY_DATE", nullable = false, length = 40)
  @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
  private ZonedDateTime metadataWhen;
  @NotEmpty
  @Column(name = "METADATA_VERSION", nullable = false, length = 40)
  private String metadataVersion;

  public MetadataEntity()
  {
  }

  public MetadataEntity (String metadataCall, ZonedDateTime metadataWhen, String metadataVersion)
  {
    this.metadataCall = metadataCall;
    this.metadataWhen = metadataWhen;
    this.metadataVersion = metadataVersion;
  }

  public MetadataEntity (MetadataDto metadataDto)
  {
    this.setMetadataVersion(metadataDto.getMetadataVersion());
    this.setMetadataWhen(metadataDto.getMetadataWhen());
    this.setMetadataCall(metadataDto.getMetadataCall());
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public JourneyEntity getJourney()
  {
    return journey;
  }

  public void setJourney(JourneyEntity journey)
  {
    this.journey = journey;
  }

  public String getMetadataCall()
  {
    return metadataCall;
  }

  public void setMetadataCall(String metadataCall)
  {
    this.metadataCall = metadataCall;
  }

  public ZonedDateTime getMetadataWhen()
  {
    return metadataWhen;
  }

  public void setMetadataWhen(ZonedDateTime metadataWhen)
  {
    this.metadataWhen = metadataWhen;
  }

  public String getMetadataVersion()
  {
    return metadataVersion;
  }

  public void setMetadataVersion(String metadataVersion)
  {
    this.metadataVersion = metadataVersion;
  }
}
