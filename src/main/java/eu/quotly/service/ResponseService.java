package eu.quotly.service;

import eu.quotly.config.ErrorCode;
import eu.quotly.config.ResponseStatus;
import eu.quotly.dto.ErrorResponseDto;
import eu.quotly.dto.ResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ResponseService {
  public Response createResponse(Response.Status httpStatus, Object data) {
    return Response
      .status(httpStatus)
      .entity(new ResponseDto(ResponseStatus.SUCCESS.getName(), data))
      .build();
  }

  public Response createErrorResponse(Response.Status httpStatus, ErrorCode errorCode) {
    return Response
      .status(httpStatus)
      .entity(new ErrorResponseDto(ResponseStatus.ERROR.getName(), errorCode.getValue()))
      .build();
  }
}
