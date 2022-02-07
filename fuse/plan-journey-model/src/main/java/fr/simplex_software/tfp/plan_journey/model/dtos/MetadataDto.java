package fr.simplex_software.tfp.plan_journey.model.dtos;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import fr.simplex_software.tfp.plan_journey.model.converters.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import fr.simplex_software.tfp.plan_journey.model.serializers.*;

import javax.json.bind.annotation.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import java.io.*;
import java.time.*;

import static java.time.format.DateTimeFormatter.*;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("metadata")
public class MetadataDto implements Serializable
{
  @JsonProperty("call")
  @JsonbProperty("call")
  private String metadataCall;
  @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
  @JsonProperty("date")
  @JsonbProperty("date")
  @JsonSerialize(using = ZonedDateTimeSerializer.class)
  @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss xxx")
  //@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss xxx")
  private ZonedDateTime metadataWhen;
  @JsonProperty("version")
  @JsonbProperty("version")
  private String metadataVersion;

  public MetadataDto()
  {
  }

  public MetadataDto(String metadataCall, ZonedDateTime metadataWhen, String metadataVersion)
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
