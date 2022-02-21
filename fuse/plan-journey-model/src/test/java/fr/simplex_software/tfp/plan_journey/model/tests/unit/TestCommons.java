package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;

import javax.xml.bind.*;
import java.io.*;
import java.time.*;
import java.util.*;

public class TestCommons
{
  public static File marshalJourneyToXmlFile(Object journey, File xml)
  {
    try
    {
      Marshaller marshaller = JAXBContext.newInstance(journey.getClass()).createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(journey, xml);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return xml;
  }

  public static Object unmarshalXmlFileToJourneyDto(File xml)
  {
    Object journey = null;
    try
    {
      Unmarshaller unmarshaller = JAXBContext.newInstance(JourneyDto.class).createUnmarshaller();
      journey = unmarshaller.unmarshal(xml);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return journey;
  }

  public static Object unmarshalXmlFileToJourneyEntity(File xml)
  {
    Object journey = null;
    try
    {
      Unmarshaller unmarshaller = JAXBContext.newInstance(JourneyEntity.class).createUnmarshaller();
      journey = unmarshaller.unmarshal(xml);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return journey;
  }

  public static JourneyDto getJourneyDto()
  {
    MetadataDto metadataDto = new MetadataDto("metadataCall", ZonedDateTime.now(), "metadataVersion");
    List<DestinationDto> destinationDtos = List.of(new DestinationDto("stationName", "platformId"));
    ResultDto resultDto = new ResultDto(destinationDtos);
    return new JourneyDto("MyJourney822", resultDto, metadataDto);
  }

  public static JourneyEntity getJourneyEntity()
  {
    JourneyEntity journeyEntity = new JourneyEntity(getJourneyDto());
    journeyEntity.getResult().getDestinations().get(0).setResult(journeyEntity.getResult());
    return journeyEntity;
  }
}
