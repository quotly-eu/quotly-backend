package eu.quotly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import eu.quotly.config.ErrorCode;
import eu.quotly.config.ResponseStatus;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"status", "errorCode"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDto(String status, Long errorCode) {
  public static class Builder {
    private String status;
    private Long errorCode;

    public Builder withResponseStatus(ResponseStatus status) {
      if (status != null) {
        this.status = status.getName();
      }
      return this;
    }

    public Builder withErrorCode(ErrorCode errorCode) {
      if (errorCode != null) {
        this.errorCode = errorCode.getValue();
      }
      return this;
    }

    public ErrorResponseDto build() {
      return new ErrorResponseDto(status, errorCode);
    }
  }
}
