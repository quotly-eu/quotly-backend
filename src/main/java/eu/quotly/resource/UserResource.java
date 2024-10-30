package eu.quotly.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  @GET
  public Response getUsers() {
    return Response.ok().build();
  }

  @GET
  @Path("/{userId}")
  public Response getUser(@PathParam("userId") String userId) {
    return Response.ok().build();
  }

  @GET
  @Path("/{userId}/reactions")
  public Response getUserReactions(@PathParam("userId") String userId) {
    return Response.ok().build();
  }

  @GET
  @Path("/{userId}/roles")
  public Response getUserRoles(@PathParam("userId") String userId) {
    return Response.ok().build();
  }

  @GET
  @Path("/{userId}/saved-quotes")
  public Response getUserSavedQuotes(@PathParam("userId") String userId) {
    return Response.ok().build();
  }
}
