package fr.simplex_software.tfp.plan_journey.model.converters;

import javax.persistence.*;
import java.sql.*;
import java.time.*;

@Converter(autoApply = true)
public class ZonedDateTime2TimestampConverter implements AttributeConverter<ZonedDateTime, Timestamp>
{
  @Override
  public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime)
  {
    return zonedDateTime != null ? Timestamp.valueOf(zonedDateTime.toLocalDateTime()) : null;
  }

  @Override
  public ZonedDateTime convertToEntityAttribute(Timestamp timestamp)
  {
    return timestamp != null ? timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()) : null;
  }
}
