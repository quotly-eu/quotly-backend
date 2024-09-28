package eu.quotly.filter;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class LoggingFilter implements ContainerResponseFilter, ExceptionMapper<WebApplicationException> {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public void filter(
    final ContainerRequestContext containerRequestContext,
    final ContainerResponseContext containerResponseContext
  ) {
    int status = containerResponseContext.getStatus();
    String method = containerRequestContext.getMethod();
    String url = containerRequestContext.getUriInfo().getRequestUri().toString();
    LOGGER.info("Response {status={}, method=\"{}\", url=\"{}\"}", status, method, url);
  }

  @Override
  public Response toResponse(final WebApplicationException webApplicationException) {
    String exceptionMessage = webApplicationException.getMessage();
    String[] messageParts = exceptionMessage.split(":", 2);

    LOGGER.info("WebApplicationException {type=\"{}\", message=\"{}\"}", messageParts[0], messageParts[1].trim());
    return webApplicationException.getResponse();
  }
}
