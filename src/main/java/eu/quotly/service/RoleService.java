package eu.quotly.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class RoleService {
  @Inject
  ResponseService responseService;

  public Response getAllRoles() {
    return responseService.createResponse(Response.Status.OK, null);
  }

  public Response getRoleById(Long roleId) {
    return responseService.createResponse(Response.Status.OK, null);
  }
}
