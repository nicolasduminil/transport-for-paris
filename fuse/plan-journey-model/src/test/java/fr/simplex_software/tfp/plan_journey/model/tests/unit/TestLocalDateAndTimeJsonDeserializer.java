package fr.simplex_software.tfp.plan_journey.model.tests.unit;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import fr.simplex_software.tfp.plan_journey.model.serializers.*;
import org.codehaus.jackson.map.ext.*;
import org.junit.*;

import java.io.*;
import java.time.*;

import static org.junit.Assert.*;

public class TestLocalDateAndTimeJsonDeserializer
{
  @Test
  public void testDeserializeLocalDateTime() throws IOException
  {
    LocalDateTime localDateTime =
      new LocalDateTimeDeserializer().deserialize(new ObjectMapper().getFactory()
        .createParser(new StringReader("\"2022-02-02 17:59:23\"")), new ObjectMapper()
        .getDeserializationContext());
    assertEquals(localDateTime, LocalDateTime.of(2022, Month.FEBRUARY, 2, 17, 59, 23));
  }
}
