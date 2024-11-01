package eu.quotly.service;

import eu.quotly.config.ErrorCode;
import eu.quotly.dto.UserDto;
import eu.quotly.entity.UserEntity;
import eu.quotly.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  @Inject UserRepository userRepository;
  @Inject ResponseService responseService;

  public Response getAllUsers(String startTime, String endTime, String searchQuery, Integer pageIndex, Integer pageSize) {
    if (pageIndex < 0 || pageSize < 1) {
      return responseService.createErrorResponse(Response.Status.BAD_REQUEST, ErrorCode.INVALID_PAGE_INDEX_OR_SIZE);
    }

    LocalDateTime startDateTime;
    try {
      if (startTime == null || startTime.isBlank()) {
        startDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
      } else {
        startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
      }
    } catch (Exception exception) {
      return responseService.createErrorResponse(Response.Status.BAD_REQUEST, ErrorCode.DATE_PARSING_ERROR);
    }

    LocalDateTime endDateTime;
    try {
      if (endTime == null || endTime.isBlank()) {
        endDateTime = LocalDateTime.now();
      } else {
        endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
      }
    } catch (Exception exception) {
      return responseService.createErrorResponse(Response.Status.BAD_REQUEST, ErrorCode.DATE_PARSING_ERROR);
    }

    List<UserEntity> users;
    if (searchQuery == null || searchQuery.isBlank()) {
      users = userRepository.filterByCreationTimeOrByDisplayName(
        startDateTime, endDateTime, "", pageIndex, pageSize);
    } else {
      users = userRepository.filterByCreationTimeOrByDisplayName(
        startDateTime, endDateTime, searchQuery, pageIndex, pageSize);
    }

    LOGGER.info("Found {} users between {} and {} that starts with {}",
      users.size(), startDateTime, endDateTime, searchQuery);
    return responseService.createResponse(Response.Status.OK, users);
  }

  public Response getUserByDiscordId(String discordId) {
    UserEntity foundUser = userRepository.findByDiscordId(discordId);
    if (foundUser == null) {
      return responseService.createErrorResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
    }

    return responseService.createResponse(Response.Status.OK, new UserDto(
      foundUser.getDiscordId(),
      foundUser.getDisplayName(),
      foundUser.getEmailAddress(),
      foundUser.getCreationTime()
    ));
  }

  public Response getAllUserReactions(String discordId) {
    UserEntity foundUser = userRepository.findByDiscordId(discordId);
    if (foundUser == null) {
      return responseService.createErrorResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
    }
    return responseService.createResponse(Response.Status.OK, foundUser.getReactions());
  }

  public Response getAllUserRoles(String discordId) {
    UserEntity foundUser = userRepository.findByDiscordId(discordId);
    if (foundUser == null) {
      return responseService.createErrorResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
    }
    return responseService.createResponse(Response.Status.OK, foundUser.getRoles());
  }

  public Response getAllUserSavedQuotes(String discordId) {
    UserEntity foundUser = userRepository.findByDiscordId(discordId);
    if (foundUser == null) {
      return responseService.createErrorResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
    }
    return responseService.createResponse(Response.Status.OK, foundUser.getSavedQuotes());
  }
}
