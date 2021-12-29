package fr.simplex_software.tfp.plan_journey.model.converters;

import javax.xml.bind.annotation.adapters.*;
import java.time.*;
import java.time.format.*;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime>
{
  private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  @Override
  public String marshal(LocalDateTime dateTime)
  {
    return dateTime.format(dateFormat);
  }

  @Override
  public LocalDateTime unmarshal(String dateTime)
  {
    return LocalDateTime.parse(dateTime, dateFormat);
  }
}

