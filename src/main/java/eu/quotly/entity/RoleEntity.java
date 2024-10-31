package eu.quotly.entity;

import eu.quotly.Constants;
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
@Table(name = "roles", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends PanacheEntityBase {
  @Id
  @Column(name = "role_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
  @SequenceGenerator(name = "roles_seq", sequenceName = "roles_sequence", allocationSize = 1)
  private Long roleId;

  @Column(name = "name", length = Constants.DB_EXTRA_SMALL_STRING_LENGTH)
  private String name;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime creationTime = LocalDateTime.now();
}
