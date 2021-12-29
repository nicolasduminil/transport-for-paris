package fr.simplex_software.tfp.plan_journey.model.dtos;

import com.fasterxml.jackson.annotation.*;
import fr.simplex_software.tfp.plan_journey.model.converters.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import java.io.*;
import java.time.*;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("metadata")
public class MetadataDto implements Serializable
{
  private String metadataCall;
  @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
  private LocalDateTime metadataWhen;
  private String metadataVersion;

  public MetadataDto()
  {
  }

  public MetadataDto(String metadataCall, LocalDateTime metadataWhen, String metadataVersion)
  {
    this.metadataCall = metadataCall;
    this.metadataWhen = metadataWhen;
    this.metadataVersion = metadataVersion;
  }

  public MetadataDto (MetadataEntity metadataEntity)
  {
    this.metadataCall = metadataEntity.getMetadataCall();
    this.metadataVersion = metadataEntity.getMetadataVersion();
    this.metadataWhen = metadataEntity.getMetadataWhen();
  }

  public String getMetadataCall()
  {
    return metadataCall;
  }

  public void setMetadataCall(String metadataCall)
  {
    this.metadataCall = metadataCall;
  }

  public LocalDateTime getMetadataWhen()
  {
    return metadataWhen;
  }

  public void setMetadataWhen(LocalDateTime metadataWhen)
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
