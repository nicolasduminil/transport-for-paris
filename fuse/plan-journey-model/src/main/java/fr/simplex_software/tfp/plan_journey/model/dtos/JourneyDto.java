package fr.simplex_software.tfp.plan_journey.model.dtos;

import com.fasterxml.jackson.annotation.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import lombok.extern.slf4j.*;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlRootElement(name = "journey")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("journey")
@Slf4j
public class JourneyDto implements Serializable
{
  private String name;
  private ResultDto result;
  private MetadataDto metadata;

  public JourneyDto()
  {
  }

  public JourneyDto(String name, ResultDto result, MetadataDto metadata)
  {
    this.name = name;
    this.result = result;
    this.metadata = metadata;
  }

  public JourneyDto(JourneyEntity journeyEntity)
  {
    this(journeyEntity.getName(), new ResultDto(journeyEntity.getResult()), new MetadataDto(journeyEntity.getMetadata()));
  }

  public JourneyDto(ResponseDto responseDto)
  {
    this(new Random().ints(97, 122).limit(10).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(),
      responseDto.getResult(), responseDto.getMetadata());
  }

  public JourneyDto(String name, ResponseDto responseDto)
  {
    this(name, responseDto.getResult(), responseDto.getMetadata());
  }

  public ResultDto getResult()
  {
    return result;
  }

  public void setResult(ResultDto result)
  {
    this.result = result;
  }

  public MetadataDto getMetadata()
  {
    return metadata;
  }

  public void setMetadata(MetadataDto metadata)
  {
    this.metadata = metadata;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
