package eu.quotly.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/quotes")
public class QuoteResource {
  @GET
  public String getQuoteList() {
    return "{}";
  }

}
