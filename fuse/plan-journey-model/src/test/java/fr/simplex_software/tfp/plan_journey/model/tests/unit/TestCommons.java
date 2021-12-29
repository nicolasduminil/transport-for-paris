package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;

import javax.xml.bind.*;
import java.io.*;

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
}
