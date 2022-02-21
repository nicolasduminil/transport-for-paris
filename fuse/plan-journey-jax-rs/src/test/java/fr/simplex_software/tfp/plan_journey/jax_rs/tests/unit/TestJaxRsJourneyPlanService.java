package fr.simplex_software.tfp.plan_journey.jax_rs.tests.unit;

import fr.simplex_software.tfp.plan_journey.model.dtos.*;
import fr.simplex_software.tfp.plan_journey.model.tests.unit.*;
import org.apache.http.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.net.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TestJaxRsJourneyPlanService extends TestCommons
{
  @Mock
  private WebTarget mockWebTarget;
  @Mock
  private Invocation.Builder mockBuilder;
  @Mock
  private Response mockResponse;

  @Test
  public void testCreateJourney()
  {
    mockResponse = Response.created(any (URI.class)).entity(getJourneyDto()).build();
    when (mockWebTarget.request(MediaType.APPLICATION_XML)).thenReturn(mockBuilder);
    when (mockBuilder.post(Entity.entity(any (JourneyDto.class), MediaType.APPLICATION_XML))).thenReturn(mockResponse);
    Response response = mockWebTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(new JourneyDto(), MediaType.APPLICATION_XML));
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_CREATED);
    JourneyDto journeyDto = response.readEntity(JourneyDto.class);
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isNotEmpty();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }

  @Test
  public void testGetJourneys()
  {
    mockResponse = Response.ok().entity(List.of(getJourneyDto())).build();
    when (mockWebTarget.request(MediaType.APPLICATION_XML)).thenReturn(mockBuilder);
    when (mockBuilder.get()).thenReturn(mockResponse);
    Response response = mockWebTarget.request(MediaType.APPLICATION_XML).get();
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
    List<JourneyDto> journeyDtoList = response.readEntity(new GenericType<>(){});
    assertThat(journeyDtoList.isEmpty()).isFalse();
    assertThat(journeyDtoList.size()).isEqualTo(1);
    JourneyDto journeyDto = journeyDtoList.get(0);
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }

  @Test
  public void testGetJourney()
  {
    mockResponse = Response.ok().entity(getJourneyDto()).build();
    when (mockWebTarget.request(MediaType.APPLICATION_XML)).thenReturn(mockBuilder);
    when (mockBuilder.get()).thenReturn(mockResponse);
    Response response = mockWebTarget.request(MediaType.APPLICATION_XML).get();
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
    JourneyDto journeyDto = response.readEntity(JourneyDto.class);
    assertThat(journeyDto).isNotNull();
    assertThat(journeyDto.getName()).isEqualTo("MyJourney822");
  }
}
