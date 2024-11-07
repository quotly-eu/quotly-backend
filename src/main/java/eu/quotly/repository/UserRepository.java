package eu.quotly.repository;

import eu.quotly.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
  public List<UserEntity> filterByCreationTimeOrByDisplayName(
    LocalDateTime startTime,
    LocalDateTime endTime,
    String searchQuery,
    Integer pageIndex,
    Integer pageSize
  ) {
    if (searchQuery == null || searchQuery.isBlank()) {
      return find("creationTime BETWEEN ?1 AND ?2", startTime, endTime).page(pageIndex, pageSize).list();
    } else {
      return find("(creationTime BETWEEN ?1 AND ?2) AND (displayName LIKE ?3)",
        startTime, endTime, "%" + searchQuery + "%").page(pageIndex, pageSize).list();
    }
  }


  public UserEntity findByDiscordId(final String discordId) {
    return find("discordId", discordId).firstResult();
  }
}
