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

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends PanacheEntityBase {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_SEQ")
  @SequenceGenerator(name = "users_SEQ", sequenceName = "users_SEQ", allocationSize = 1)
  private Long userId;

  @Column(name = "discord_id")
  private String discordId;

  @Column(name = "email_address")
  private String emailAddress;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "created_at")
  private LocalDateTime creationTime;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;
}
