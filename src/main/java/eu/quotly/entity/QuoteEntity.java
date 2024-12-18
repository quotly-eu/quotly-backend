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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "quotes", schema = "quotly")
@EqualsAndHashCode(callSuper = true)
public class QuoteEntity extends PanacheEntityBase {
  @Id
  @Column(name = "quote_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quotes_seq")
  @SequenceGenerator(name = "quotes_seq", sequenceName = "quotes_sequence", allocationSize = 1)
  private Long quoteId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(name = "quote", length = Constants.DB_EXTRA_LARGE_STRING_LENGTH)
  private String quote;

  @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<ReactionEntity> reactions;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime creationTime = LocalDateTime.now();

  @Column(name = "changed_at")
  private LocalDateTime modificationTime;

  @Column(name = "deleted_at")
  private LocalDateTime deletionTime;

  @Override
  public String toString() {
    return "QuoteEntity {"
      + "quoteId=" + quoteId
      + ", userId=" + user.getUserId()
      + ", quote=" + quote
      + ", reactionCount=" + (reactions != null ? reactions.size() : 0)
      + ", creationTime=" + creationTime
      + ", modificationTime=" + modificationTime
      + ", deletionTime=" + deletionTime
      + "}";
  }
}
