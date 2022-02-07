package fr.simplex_software.tfp.plan_journey.model.serializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime>
{
  public LocalDateTimeSerializer()
  {
    super(LocalDateTime.class);
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
  {
    jsonGenerator.writeString(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }
}
