package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import com.fasterxml.jackson.databind.*;
import fr.simplex_software.tfp.plan_journey.model.serializers.*;
import org.junit.*;

import java.io.*;
import java.time.*;

import static org.junit.Assert.*;

public class TestZonedDateAndTimeJsonDeserializer
{
  @Test
  public void testDeserializeZonedDateTime() throws IOException
  {
    ZonedDateTime zonedDateTime =
      new ZonedDateTimeDeserializer().deserialize(new ObjectMapper().getFactory()
        .createParser(new StringReader("\"2022-02-02T17:59:23+01:00\"")), new ObjectMapper()
        .getDeserializationContext());
    LocalDateTime now = LocalDateTime.of(2022, Month.FEBRUARY, 2, 17, 59, 23);
    assertEquals(zonedDateTime, now.atOffset(ZoneOffset.of("+01:00")).toZonedDateTime());
  }
}
