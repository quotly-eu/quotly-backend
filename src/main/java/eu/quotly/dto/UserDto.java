package eu.quotly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.time.LocalDateTime;

@JsonbPropertyOrder({"discordId", "displayName", "emailAddress", "creationTime"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
  String discordId,
  String displayName,
  String emailAddress,
  LocalDateTime creationTime
) {
}
