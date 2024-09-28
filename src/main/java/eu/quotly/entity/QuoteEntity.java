package eu.quotly.entity;

import eu.quotly.Constants;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quotes", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class QuoteEntity extends PanacheEntityBase {
  @Id
  @Column(name = "quote_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_seq")
  @SequenceGenerator(name = "quote_seq", sequenceName = "quote_sequence", allocationSize = 1)
  private Long quoteId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(name = "quote", length = Constants.DB_EXTRA_LARGE_STRING_LENGTH)
  private String quote;

  @Column(name = "created_at")
  private LocalDateTime creationTime;

  @Column(name = "changed_at")
  private LocalDateTime changeTime;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "QuoteEntity {"
      + "quoteId=" + quoteId
      + ", quote=" + quote
      + ", creationTime=" + creationTime
      + ", changeTime=" + changeTime
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
