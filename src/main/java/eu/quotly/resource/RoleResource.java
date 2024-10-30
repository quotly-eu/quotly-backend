package eu.quotly.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
  @GET
  public Response getRoles() {
    return Response.ok().build();
  }

  @GET
  @Path("/{roleId}")
  public Response getRole(@PathParam("roleId") String roleId) {
    return Response.ok().build();
  }
}
