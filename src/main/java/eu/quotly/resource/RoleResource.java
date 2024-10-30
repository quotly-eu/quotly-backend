package eu.quotly.resource;

import eu.quotly.service.RoleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
  @Inject
  RoleService roleService;

  @GET
  public Response getRoles() {
    return roleService.getAllRoles();
  }

  @GET
  @Path("/{roleId}")
  public Response getRole(@PathParam("roleId") Long roleId) {
    return roleService.getRoleById(roleId);
  }
}
