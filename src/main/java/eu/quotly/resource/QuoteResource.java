package eu.quotly.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteResource {
  @GET
  public Response getQuotes() {
    return Response.ok().build();
  }

  @GET
  @Path("/{quoteId}")
  public Response getQuote(@PathParam("quoteId") String quoteId) {
    return Response.ok().build();
  }

  @GET
  @Path("/{quoteId}/reactions")
  public Response getQuoteReactions(@PathParam("quoteId") String quoteId) {
    return Response.ok().build();
  }

  @GET
  @Path("/{quoteId}/comments")
  public Response getQuoteComments(@PathParam("quoteId") String quoteId) {
    return Response.ok().build();
  }
}

