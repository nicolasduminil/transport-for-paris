package fr.simplex_software.tfp.plan_journey.provider;

import javax.ws.rs.container.*;
import java.io.*;

public class CorsFilter implements ContainerResponseFilter
{
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
  {
    responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    responseContext.getHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
    responseContext.getHeaders().add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
  }
}
