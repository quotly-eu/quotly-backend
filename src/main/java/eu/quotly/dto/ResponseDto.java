package eu.quotly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import eu.quotly.config.ResponseStatus;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"status", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDto(String status, Object data) {
  public static class Builder {
    private String status;
    private Object data;

    public Builder withResponseStatus(ResponseStatus status) {
      if (status != null) {
        this.status = status.getName();
      }
      return this;
    }

    public Builder withData(Object data) {
      this.data = data;
      return this;
    }

    public ResponseDto build() {
      return new ResponseDto(status, data);
    }
  }
}
