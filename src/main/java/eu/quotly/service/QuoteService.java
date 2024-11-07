package eu.quotly.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class QuoteService {
  @Inject
  ResponseService responseService;

  public Response getAllQuotes() {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getQuoteById(Long quoteId) {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getAllQuoteReactions(Long quoteId) {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getAllQuoteComments(Long quoteId) {
    return responseService.createResponse(Response.Status.OK, null);
  }
}
