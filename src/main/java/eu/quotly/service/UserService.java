package eu.quotly.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserService {
  @Inject
  ResponseService responseService;

  public Response getAllUsers() {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getUserByDiscordId(String discordId) {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getAllUserReactions(String discordId) {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getAllUserRoles(String discordId) {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getAllUserSavedQuotes(String discordId) {
    return responseService.createResponse(Response.Status.OK, null);
  }
}
