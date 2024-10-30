package eu.quotly.service;

import eu.quotly.dto.ErrorResponseDto;
import eu.quotly.dto.ResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ResponseService {
  public Response createResponse(Response.Status httpStatus, ResponseDto responseDto) {
    return Response
      .status(httpStatus)
      .entity(responseDto)
      .build();
  }

  public Response createErrorResponse(Response.Status httpStatus, ErrorResponseDto errorResponseDto) {
    return Response
      .status(httpStatus)
      .entity(errorResponseDto)
      .build();
  }
}
