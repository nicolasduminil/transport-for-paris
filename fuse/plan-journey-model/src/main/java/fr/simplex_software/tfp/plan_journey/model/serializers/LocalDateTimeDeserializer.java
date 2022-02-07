package fr.simplex_software.tfp.plan_journey.model.serializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime>
{
  public LocalDateTimeDeserializer()
  {
    super(LocalDateTime.class);
  }

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
  {
    return LocalDateTime.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
