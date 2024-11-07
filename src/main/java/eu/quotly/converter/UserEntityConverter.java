package eu.quotly.converter;

import eu.quotly.dto.UserDto;
import eu.quotly.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserEntityConverter {
  public UserDto toUserDto(UserEntity userEntity) {
    return new UserDto(
      userEntity.getDiscordId(),
      userEntity.getDisplayName(),
      userEntity.getEmailAddress(),
      userEntity.getCreationTime()
    );
  }
}
