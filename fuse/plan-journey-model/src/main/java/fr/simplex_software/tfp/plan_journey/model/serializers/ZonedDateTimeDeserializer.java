package fr.simplex_software.tfp.plan_journey.model.serializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

import static java.time.format.DateTimeFormatter.*;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime>
{
  public ZonedDateTimeDeserializer()
  {
    super(ZonedDateTime.class);
  }

  @Override
  public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
  {
    System.out.println ("### ZonedDateTimeDeserializer.deserialize()");
    return ZonedDateTime.parse(jsonParser.readValueAs(String.class), ISO_ZONED_DATE_TIME);
  }
}
