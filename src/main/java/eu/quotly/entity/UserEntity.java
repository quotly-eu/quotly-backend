package eu.quotly.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

  @Column(name = "discord_id", length = 50)
  private String discordId;

  @Column(name = "email_address", length = 400)
  private String emailAddress;

  @Column(name = "display_name", length = 50)
  private String displayName;

  @Column(name = "created_at")
  private LocalDateTime creationTime;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "UserEntity {"
      + "userId=" + userId
      + ", discordId=" + discordId
      + ", emailAddress=" + emailAddress
      + ", displayName=" + displayName
      + ", creationTime=" + creationTime
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
