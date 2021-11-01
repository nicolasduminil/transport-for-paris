package fr.simplex_software.tfp.jpa.producer;

import javax.enterprise.inject.*;
import javax.inject.*;
import java.util.*;

public class QueryParametersProducer
{
  @Produces
  @Named("queryParameters")
  public Map<String, Object> queryParameters()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("id", "${header.id}");
    return params;
  }
}
