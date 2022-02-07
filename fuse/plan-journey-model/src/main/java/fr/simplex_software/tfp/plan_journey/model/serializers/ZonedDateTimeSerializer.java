package fr.simplex_software.tfp.plan_journey.model.serializers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

import static java.time.format.DateTimeFormatter.*;

public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime>
{
  public ZonedDateTimeSerializer()
  {
    super(ZonedDateTime.class);
  }

  @Override
  public void serialize(ZonedDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
  {
    System.out.println ("### ZonedDateTimeSerializer.serialize()");
    jsonGenerator.writeString(zonedDateTime.format(ISO_ZONED_DATE_TIME));
  }
}
