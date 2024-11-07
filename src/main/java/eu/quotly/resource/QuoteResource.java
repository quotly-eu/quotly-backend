package eu.quotly.resource;

import eu.quotly.service.QuoteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteResource {
  @Inject
  QuoteService quoteService;

  @GET
  public Response getQuotes() {
    return quoteService.getAllQuotes();
  }

  @GET
  @Path("/{quoteId}")
  public Response getQuote(@PathParam("quoteId") Long quoteId) {
    return quoteService.getQuoteById(quoteId);
  }

  @GET
  @Path("/{quoteId}/reactions")
  public Response getQuoteReactions(@PathParam("quoteId") Long quoteId) {
    return quoteService.getAllQuoteReactions(quoteId);
  }

  @GET
  @Path("/{quoteId}/comments")
  public Response getQuoteComments(@PathParam("quoteId") Long quoteId) {
    return quoteService.getAllQuoteComments(quoteId);
  }
}

