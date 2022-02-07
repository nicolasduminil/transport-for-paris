package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import fr.simplex_software.tfp.plan_journey.model.serializers.*;
import org.junit.*;

import java.io.*;
import java.time.*;

import static org.junit.Assert.*;

public class TestZonedDateAndTimeJsonSerializer
{
  @Test
  public void testSerializeZonedDateTime() throws IOException
  {
    Writer jsonWriter = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
    LocalDateTime now = LocalDateTime.of(2022, Month.FEBRUARY, 2, 17, 59, 23);
    new ZonedDateTimeSerializer().serialize(now.atOffset(ZoneOffset.of("+01:00")).toZonedDateTime(), jsonGenerator, serializerProvider);
    jsonGenerator.flush();
    assertEquals("\"2022-02-02T17:59:23+01:00\"", jsonWriter.toString());
  }
}
