package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import org.junit.*;
import org.junit.runners.*;

import java.io.*;
import java.time.*;
import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJourneyDto extends TestCommons
{
  @Test
  public void testMarshalJourneyDto()
  {
    JourneyDto journeyDto = new JourneyDto("MyJourney", new ResultDto(List.of(new DestinationDto("stationName1",
      "platformId1"), new DestinationDto("stationName2", "platformId2"))),
      new MetadataDto("metadataCall", LocalDateTime.now(), "metadataVersion"));
    assertTrue(marshalJourneyToXmlFile(journeyDto, new File("journey1.xml")).exists());
  }

  @Test
  public void testUnmarshalJourneyDto()
  {
    JourneyDto journeyDto = (JourneyDto) unmarshalXmlFileToJourneyDto(new File ("journey1.xml"));
    assertNotNull (journeyDto);
    assertNotNull(journeyDto.getMetadata());
    assertNotNull(journeyDto.getResult());
    assertEquals("platformId1", journeyDto.getResult().getDestinations().get(0).getPlatformId());
    assertEquals("metadataCall", journeyDto.getMetadata().getMetadataCall());
  }
}