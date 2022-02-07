package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import fr.simplex_software.tfp.plan_journey.model.serializers.*;
import org.junit.*;

import java.io.*;
import java.time.*;

import static org.junit.Assert.*;

public class TestLocalDateAndTimeJsonSerializer
{
  @Test
  public void testSerializeLocalDateTime() throws IOException
  {
    Writer jsonWriter = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
    new LocalDateTimeSerializer().serialize(LocalDateTime.of(2022, Month.FEBRUARY, 2, 17, 59, 23), jsonGenerator, serializerProvider);
    jsonGenerator.flush();
    assertEquals("\"2022-02-02 17:59:23\"", jsonWriter.toString());
  }
}
