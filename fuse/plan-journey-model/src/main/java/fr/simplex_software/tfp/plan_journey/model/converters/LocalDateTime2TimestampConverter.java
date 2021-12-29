package fr.simplex_software.tfp.plan_journey.model.converters;

import javax.persistence.*;
import java.sql.*;
import java.time.*;

public class LocalDateTime2TimestampConverter implements AttributeConverter<LocalDateTime, Timestamp>
{
  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime)
  {
    return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp timestamp)
  {
    return timestamp != null ? timestamp.toLocalDateTime() : null;
  }
}
