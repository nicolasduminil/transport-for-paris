package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import fr.simplex_software.tfp.plan_journey.model.entities.*;
import org.junit.*;
import org.junit.runners.*;

import java.io.*;
import java.time.*;
import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJourneyEntity extends TestCommons
{
  @Test
  public void testMarshalJourneyEntity()
  {
    JourneyEntity journeyEntity = new JourneyEntity("MyJourney", new ResultEntity(List.of(new DestinationEntity("stationName1", "platformId1"),
      new DestinationEntity("stationName2", "platformId2"))),
      new MetadataEntity("metadataCall", LocalDateTime.now(), "metadataVersion"));
    assertTrue(marshalJourneyToXmlFile(journeyEntity, new File ("journey2.xml")).exists());
  }

  @Test
  public void testUnmarshalJourneyEntity()
  {
    JourneyEntity journeyEntity = (JourneyEntity)unmarshalXmlFileToJourneyEntity(new File("journey2.xml"));
    assertNotNull (journeyEntity);
    assertEquals("MyJourney", journeyEntity.getName());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult());
    assertEquals("platformId1", journeyEntity.getResult().getDestinations().get(0).getPlatformId());
    assertEquals("metadataCall", journeyEntity.getMetadata().getMetadataCall());
  }
}
