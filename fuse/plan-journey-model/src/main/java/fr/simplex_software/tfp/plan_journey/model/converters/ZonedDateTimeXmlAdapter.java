package fr.simplex_software.tfp.plan_journey.model.converters;

import javax.xml.bind.annotation.adapters.*;
import java.time.*;

import static java.time.format.DateTimeFormatter.*;

public class ZonedDateTimeXmlAdapter extends XmlAdapter<String, ZonedDateTime>
{
  @Override
  public String marshal(ZonedDateTime dateTime)
  {
    return dateTime.format(ISO_OFFSET_DATE_TIME);
  }

  @Override
  public ZonedDateTime unmarshal(String dateTime)
  {
    return ZonedDateTime.parse(dateTime, ISO_OFFSET_DATE_TIME);
  }
}

