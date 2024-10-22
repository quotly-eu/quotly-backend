package eu.quotly.repository;

import eu.quotly.entity.QuoteEntity;
import eu.quotly.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@SuppressWarnings("unused")
@ApplicationScoped
public class QuoteRepository implements PanacheRepository<QuoteEntity> {

  @Inject
  UserRepository userRepository;

  public List<QuoteEntity> findByDiscordId(final String discordId) {
    UserEntity user = userRepository.findByDiscordId(discordId);
    return find("user", user).stream().toList();
  }
}
