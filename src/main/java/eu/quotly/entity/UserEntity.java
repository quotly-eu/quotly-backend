package eu.quotly.entity;

import eu.quotly.Constants;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends PanacheEntityBase {
  @Id
  @Column(name = "user_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  @SequenceGenerator(name = "users_seq", sequenceName = "users_sequence", allocationSize = 1)
  private Long userId;

  @Column(name = "discord_id", length = Constants.DB_EXTRA_SMALL_STRING_LENGTH)
  private String discordId;

  @Column(name = "email_address", length = Constants.DB_LARGE_STRING_LENGTH)
  private String emailAddress;

  @Column(name = "display_name", length = Constants.DB_EXTRA_SMALL_STRING_LENGTH)
  private String displayName;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<ReactionEntity> reactions;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime creationTime = LocalDateTime.now();

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "UserEntity {"
      + "userId=" + userId
      + ", discordId=" + discordId
      + ", emailAddress=" + emailAddress
      + ", displayName=" + displayName
      + ", reactionCount=" + (reactions != null ? reactions.size() : 0)
      + ", creationTime=" + creationTime
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
