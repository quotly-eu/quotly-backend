package eu.quotly.resource;

import eu.quotly.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  @Inject
  UserService userService;

  @GET
  public Response getUsers() {
    return userService.getAllUsers();
  }

  @GET
  @Path("/{discordId}")
  public Response getUser(@PathParam("discordId") String discordId) {
    return userService.getUserByDiscordId(discordId);
  }

  @GET
  @Path("/{discordId}/reactions")
  public Response getUserReactions(@PathParam("discordId") String discordId) {
    return userService.getAllUserReactions(discordId);
  }

  @GET
  @Path("/{discordId}/roles")
  public Response getUserRoles(@PathParam("discordId") String discordId) {
    return userService.getAllUserRoles(discordId);
  }

  @GET
  @Path("/{discordId}/saved-quotes")
  public Response getUserSavedQuotes(@PathParam("discordId") String discordId) {
    return userService.getAllUserSavedQuotes(discordId);
  }
}
