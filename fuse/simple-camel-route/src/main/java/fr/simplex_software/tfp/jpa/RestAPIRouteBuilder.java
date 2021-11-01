package fr.simplex_software.tfp.jpa;

import fr.simplex_software.tfp.jpa.model.*;
import org.apache.camel.builder.*;
import org.apache.camel.cdi.*;
import org.apache.camel.model.rest.*;

import javax.enterprise.context.*;

@ApplicationScoped
@ContextName("camel-jpa-context")
public class RestAPIRouteBuilder extends RouteBuilder
{

  public void configure() throws Exception
  {
    restConfiguration()
      .contextPath("/rest/api")
      .apiContextPath("/api-doc")
      .apiProperty("api.title", "Camel REST API")
      .apiProperty("api.version", "1.0")
      .apiProperty("cors", "true")
      .apiContextRouteId("doc-api")
      .component("undertow")
      .bindingMode(RestBindingMode.json);

    rest("/books").description("Books REST service")
      .get("/").description("The list of all the books")
      .route()
      .toF("jpa:%s?nativeQuery=select distinct description from orders", Order.class.getName())
      .endRest()
      .get("order/{id}").description("Details of an order by id")
      .route()
      .toF("jpa:%s?consumeDelete=false&parameters=#queryParameters&nativeQuery=select * from orders where id = :id", Order.class.getName())
      .process("jpaResultProcessor")
      .endRest();
  }
}
