package fr.simplex_software.tfp.plan_journey.services.tests;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.entities.*;
import org.junit.*;

import java.time.*;
import java.util.*;

import static org.junit.Assert.*;

public class TestMappers
{
  @Test
  public void testDestinationMapper()
  {
    DestinationDto destinationDto = new DestinationDto("stationName", "platformId");
    DestinationEntity destinationEntity = new DestinationEntity(destinationDto);
    assertEquals ("platformId", destinationEntity.getPlatformId());
    assertEquals("stationName", destinationEntity.getStationName());
    assertNull(destinationEntity.getId());
    assertNull(destinationEntity.getResult());
    destinationDto = new DestinationDto(destinationEntity);
    assertEquals("platformId", destinationDto.getPlatformId());
    assertEquals("stationName", destinationDto.getStationName());
  }

  @Test
  public void testJourneyMapper()
  {
    LocalDateTime now = LocalDateTime.now();
    JourneyDto journeyDto = new JourneyDto("MyJourney", new ResultDto(new ArrayList<>()), new MetadataDto("metadataCall", now, "metadataVersion"));
    JourneyEntity journeyEntity = new JourneyEntity(journeyDto);
    assertNotNull(journeyEntity.getResult());
    assertNotNull(journeyEntity.getMetadata());
    assertNotNull(journeyEntity.getResult().getJourney());
    assertNull (journeyEntity.getResult().getId());
    assertNotNull(journeyEntity.getResult().getDestinations());
    assertNotNull(journeyEntity.getMetadata().getJourney());
    assertEquals("metadataCall", journeyEntity.getMetadata().getMetadataCall());
    assertEquals (now, journeyEntity.getMetadata().getMetadataWhen());
    assertNull(journeyEntity.getMetadata().getId());
    assertEquals ("metadataVersion", journeyEntity.getMetadata().getMetadataVersion());
    journeyDto = new JourneyDto(journeyEntity);
    assertNotNull(journeyDto.getResult());
    assertNotNull(journeyDto.getMetadata());
    assertNotNull(journeyDto.getResult().getDestinations());
    assertEquals("metadataCall", journeyDto.getMetadata().getMetadataCall());
    assertEquals (now, journeyDto.getMetadata().getMetadataWhen());
    assertEquals ("metadataVersion", journeyDto.getMetadata().getMetadataVersion());
  }

  @Test
  public void testMetadataMapper()
  {
    LocalDateTime now = LocalDateTime.now();
    MetadataDto metadataDto = new MetadataDto("metadataCall", now, "metadataVersion");
    MetadataEntity metadataEntity = new MetadataEntity(metadataDto);
    assertEquals("metadataCall", metadataEntity.getMetadataCall());
    assertEquals (now, metadataEntity.getMetadataWhen());
    assertNull(metadataEntity.getId());
    assertEquals ("metadataVersion", metadataEntity.getMetadataVersion());
    metadataDto = new MetadataDto(metadataEntity);
    assertEquals("metadataCall", metadataDto.getMetadataCall());
    assertEquals (now, metadataDto.getMetadataWhen());
    assertEquals ("metadataVersion", metadataDto.getMetadataVersion());
  }

  @Test
  public void testResultMapper()
  {
    ResultDto resultDto = new ResultDto(new ArrayList<>());
    ResultEntity resultEntity = new ResultEntity(resultDto);
    assertNull(resultEntity.getJourney());
    assertNull (resultEntity.getId());
    assertNotNull(resultEntity.getDestinations());
    resultDto = new ResultDto(resultEntity);
    assertNotNull(resultDto.getDestinations());
    assertEquals(0, resultDto.getDestinations().size());
  }
}
