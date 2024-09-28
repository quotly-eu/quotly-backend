package eu.quotly.repository;

import eu.quotly.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
  public UserEntity findByDiscordId(final String discordId) {
    return find("discordId", discordId).firstResult();
  }
}
